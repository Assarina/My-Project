<script setup >

 import { getAnnouncements } from "../composable/fetchAPI.js";
 import { onMounted,ref,computed } from "vue";
 import { useRouter, RouterLink } from 'vue-router';
 import Paginate from 'vue-paginate';

 const router = useRouter();
 const announcements = ref([]);
 const currentDateTime = ref(new Date());
 const showClosedAnnouncement = ref(false);
 const announcementCategory = ref("");


 onMounted( async()=>{
  const data = await getAnnouncements();
  announcements.value = data;
});

//check date close->current
const isAnnouncementInProgress = (announcement) => {
  const closeDateTime = new Date(announcement.closeDate);
  const publishDateTime = new Date(announcement.publishDate);
  
  return (
    currentDateTime.value < closeDateTime &&
    (!announcement.publishDate || publishDateTime <= currentDateTime.value)
  );
};

const filteredAnnouncements = computed(() => { //กรองไปแสดง
  const selectedCategory = announcementCategory.value; // กรองจากที่เลือก

  // Active
  const announcementsNullCloseDate = announcements.value.filter(
    (announcement) =>
      announcement.closeDate === null  &&
      announcement.announcementDisplay !== "N" &&
      (!announcement.publishDate || new Date(announcement.publishDate) <= currentDateTime.value)&& //เงื่อนไขวันที่ยังไม่ถึงไม่แสดง
        (!selectedCategory || announcement.announcementCategory === selectedCategory) // เลือกตามหมวดหมู่
  );

  const announcementsInProgress = announcements.value
    .filter(isAnnouncementInProgress)
    .filter(
      (announcement) =>
        announcement.closeDate !== null &&
        announcement.announcementDisplay !== "N" &&
        currentDateTime.value < new Date(announcement.closeDate) &&
        (!announcement.publishDate || new Date(announcement.publishDate) <= currentDateTime.value) &&
          (!selectedCategory || announcement.announcementCategory === selectedCategory)
    );

  // Closed Date
  const announcementsClosed = announcements.value
    .filter(
      (announcement) =>
        announcement.closeDate !== null &&
        announcement.announcementDisplay !== "N" &&
        currentDateTime.value >= new Date(announcement.closeDate) &&
        (!announcement.publishDate || new Date(announcement.publishDate) <= currentDateTime.value)&&
          (!selectedCategory || announcement.announcementCategory === selectedCategory)
    )
    .filter(
      (announcement) =>
        announcement.closeDate !== null 
    );

  return showClosedAnnouncement.value
    ? [...announcementsClosed]
    : [...announcementsNullCloseDate, ...announcementsInProgress];
});


//Toggle
const togglebtn = () => {
    showClosedAnnouncement.value = !showClosedAnnouncement.value;
  };

 //ShowTimeZone
 const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;

 //push
 const ViewInformations =(announcement)=>{
router.push({ name: 'UserInformations', params: { id: announcement.id}})
}

//ทำ pagination
const pageSize = 5;
const currentPage = ref(1);

const paginatedAnnouncements = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  const sortedAnnouncements = filteredAnnouncements.value
    .slice()
    .sort((a, b) => b.id - a.id); // Sort by ID in descending order
  return sortedAnnouncements.slice(startIndex, endIndex);
  });

  // ...

  const totalPages = computed(() => {
    return Math.ceil(filteredAnnouncements.value.length / pageSize);
  });

  const goToPage = (page) => {
    if (page >= 1 && page <= totalPages.value) {
      currentPage.value = page;
    }
  };

  const goToPreviousPage = () => {
    if (currentPage.value > 1) {
      currentPage.value -= 1;
    }
  };

  const goToNextPage = () => {
    if (currentPage.value < totalPages.value) {
      currentPage.value += 1;
    }
  };

</script>

<template>
    <div class ='page'>

      <h1>SIT Announcement System (SAS)</h1>
      <p class = "TimeZone "><span style="font-weight: bold; ">Date/Time shown in Timezone :&nbsp;</span> {{ timeZone }}</p> <!-- Edit Later -->

      <div class="CategoryandButton">
      <div class="selectedCategory">
          <p>Choose Category :</p>
            <select  class="borderstyleCategory" v-model="announcementCategory">
              <option value="">ทั้งหมด</option>
              <option value="ทั่วไป">ทั่วไป</option>
              <option value="ทุนการศึกษา">ทุนการศึกษา</option>
              <option value="หางาน">หางาน</option>
              <option value="ฝึกงาน">ฝึกงาน</option>
            </select>
        </div>

      <div class="Btn">
        <button @click="togglebtn">
          {{ showClosedAnnouncement ? "Active Announcement" : "Closed Announcement" }}
        </button>
    </div>
  </div>
        
    <table class="table-auto justify-center ">
       <thead>
        <tr>
          <th >No.</th>
          <th style="text-align: left; padding-left: 50px;">Title</th>
          <th style="text-align: center; padding-left: 20px;">Category</th>
        </tr>
 
        <tr v-for="(announcement, index) in paginatedAnnouncements" :key="announcement.announcementId" @click="ViewInformations(announcement)">

          <td>
            {{ index + 1 }}
          </td>
          <td style="text-align: left; padding-left: 50px;">
            {{ announcement.announcementTitle }}
          </td>
          <td style="text-align: center; padding-left: 20px;">
            {{ announcement.announcementCategory }}
          </td>
        </tr>

    </thead>
</table>

  <div v-if="filteredAnnouncements.length === 0">
        <h2>No Announcement</h2>
    </div>
  </div>

  <div class="pagination">
    <button :disabled="currentPage === 1" @click="goToPreviousPage">Previous</button>
    <button v-for="page in Math.min(15, totalPages)" :key="page" :class="{ active: currentPage === page }" @click="goToPage(page)">{{ page }}</button>
    <button :disabled="currentPage === totalPages" @click="goToNextPage">Next</button>
  </div>

 </template>

<style scoped>

  h1 {
    text-align: center; 
    font-size: 45px; 
    font-weight: bold;
    font-family: 'Montserrat', sans-serif;
    padding-top: 10px;
  }
  h2{
    text-align: center;
    font-size: 20px;
    font-weight: bold;
  }
  table{
    width: 80%; 
    margin: auto;
    margin-bottom: 20px;
    border-collapse: separate;
    border-radius: 3px;
    border-spacing: 0;
    border: 1px solid #ddd;

  }
  th {
    background-color: #e8e8e8;
    padding: 10px;
    color: #333;
    border-bottom: 2px solid #ddd;
    font-weight: bold;

  }
  tr:hover {
    background-color: #f1f1f1;
    border-color: #f1f1f1;
  }
  td{
    text-align: center;
    padding: 10px;
    border-bottom: 1px solid #ddd;
  }

  .TimeZone{
    display: flex;
    padding-top: 5px;
    padding-bottom: 10px;
    justify-content: center;
  }  

.page {
    margin-top: 25px;
}

.Btn{
  background-color: #dddddd;
  border-radius: 0.5rem;
  border: 10px solid #dddddd;
  transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .Btn:hover {
    background-color: #929292;
    border-color: #929292;
    color: #ffffff;
}
.selectedCategory{
  display: flex;
  align-items: center;
}
.borderstyleCategory{
  border-radius: 5px;
  border: 1px solid #b1b1b1;
  margin-left: 10px;
}
.CategoryandButton{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  margin-right: 10%;
  margin-left: 10%;
}
.pagination {
  margin-top: 20px;
  margin-left: 10%;
}

.pagination button {
  margin-right: 5px;
  padding: 5px 10px;
  border-radius: 5px;
  border: none;
  background-color: #f0f0f0;
  cursor: pointer;
}

.pagination button.active {
  font-weight: bold;
  background-color: #ccc;
}

.pagination button:hover {
  background-color: #ccc;
}
</style>
