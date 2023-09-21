<script setup >

 import { getAnnouncements,deleteAnnouncement } from "../composable/fetchAPI.js";
 import { onMounted,ref,computed } from "vue";
 import { useRouter, RouterLink } from 'vue-router';

 const data = ref([]);
 const showModal = ref(false);
 const selectedId = ref(null);
 const router = useRouter();
 
 
 onMounted( async()=>{
  data.value = await getAnnouncements();
});
   const options = { 
      day: 'numeric', 
      month: 'short', 
      year: 'numeric', 
      hour: 'numeric', 
      minute: '2-digit',
      hour12: false,
    };

 //ShowTimeZone
 const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;

//useRoute
const ViewInformations =(announce)=>{
router.push({ name: 'Information', params: { id:announce.id}})
}

 //show Delete Modal
 const showDeleteModal = (id) => {
    selectedId.value = id;
    showModal.value = true;
  };

//delete selected id
  const deleteSelectedAnnouncement = async () => {
    try {
      await deleteAnnouncement(selectedId.value);
      data.value = data.value.filter((announce) => announce.id !== selectedId.value);

      selectedId.value = null;
      showModal.value = false;
    } catch (err) {
      console.log(err);
    }
  };

  //sort
  const sortedData = computed(() => {
    return data.value.slice().sort((a, b) => b.id - a.id);
  });

</script>

<template>
    <div class ='page'>

      <h1>SIT Announcement System (SAS)</h1>
      <p class = "TimeZone "><span style="font-weight: bold; ">Date/Time shown in Timezone :&nbsp;</span> {{ timeZone }}</p> <!-- Edit Later -->

      <div class="AddBtn">
       <router-link to="/admin/announcement/add"> 
        <button>Add New Announcement</button> 
      </router-link>
      
    </div>
        
      <table class="table-auto justify-center ">
      <thead>
        <tr>
          <th>No.</th>
          <th style=" text-align: left;">Title</th>
          <th style=" text-align: left;">Category</th>
          <th style=" text-align: left;">Publish Date</th>
          <th style=" text-align: left;">Close Date</th>
          <th>Display</th>
          <th colspan="2">Action</th>
        </tr>
        <tr v-for="(announce, index) of sortedData" :key="announce.id">
            <td>
              {{ index + 1 }}
            </td>
            <td style="text-align: left; padding-left: 20px;">
              {{ announce.announcementTitle }}
            </td>
            <td style=" text-align: left; padding-left: 18px;">
              {{ announce.announcementCategory }}
            </td>
            <td style=" text-align: left; padding-left: 18px;">
              {{ announce.publishDate ? new Date(announce.publishDate).toLocaleString("en-GB", options) : '-'  }}
            </td>
            <td style=" text-align: left; padding-left: 15px;">
              {{ announce.closeDate ? new Date(announce.closeDate).toLocaleString("en-GB", options) : '-'  }}
            </td>
            <td>
              {{ announce.announcementDisplay }}
            </td>
            
            <td>
                <button class="ViewBtn" @click="ViewInformations(announce)">
                  View
                </button>
            </td>

            <td>
              <button class="DeleteBtn" @click="showDeleteModal(announce.id)">Delete</button>

              <div v-show="showModal" class="Modal">
              <p>Are you sure you want to delete this announcement?</p>
              <button class="CancelBtn" @click="showModal = false">Cancel</button>
              <button class="DeleteBtn" @click.prevent="deleteSelectedAnnouncement()">Delete</button>
                </div>
            </td>

          </tr>
      </thead>

    </table>
  </div>

  <div v-if="data.length === 0 ">
        <h2>No Announcement</h2>
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
    margin-bottom: 10px;
    padding-top: 10px;
    border-collapse: collapse;
    border-spacing: 0;

  }
  th {
    background-color: #e8e8e8;
    padding: 15px;
    color: #333;
    border-bottom: 2px solid #ddd;
    font-weight: bold;
  }
  td{
    text-align: center;
    padding: 10px;;
  }

  .TimeZone{
    display: flex;
    padding-top: 5px;
    padding-bottom: 10px;
    justify-content: center;
  }  
  .AddBtn{
    display: flex;
    margin: 0 auto;
    margin-bottom: 10px;
    width: fit-content;
    background-color: #dddddd;
    border-radius: 0.5rem;
    border: 10px solid #dddddd;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .AddBtn:hover {
    background-color: #929292;
    border-color: #929292;
    color: #ffffff;
}
  .ViewBtn{
    background-color: #cacaca;
    border-radius: 0.5rem;
    border: 10px solid #cacaca;
    padding: 0.1rem 1.0rem;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .ViewBtn:hover {
    background-color: #555555;
    border-color: #555555;
    color: #ffffff;
}

  .DeleteBtn{
    background-color: #ffa0a0;
    border-radius: 0.5rem;
    border: 10px solid #ffa0a0;
    padding: 0.1rem 1.0rem;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .DeleteBtn:hover {
    background-color: #ff5858;
    border-color: #ff5858;
    color: #ffffff;
}
.page {
    margin-top: 25px;
  }

.CancelBtn {
  margin: 0.5rem;
  background-color: #cacaca;
    border-radius: 0.5rem;
    border: 10px solid #cacaca;
    padding: 0.1rem 1.0rem;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .CancelBtn:hover {
    background-color: #555555;
    border-color: #555555;
    color: #ffffff;
}

  .Modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 5rem;
  border-radius: 0.5rem;
  border: 1px solid #cacaca;
}


</style>
