<script setup>
import {updateAnnouncement, getIdAnnouncement} from '../composable/fetchAPI.js'
import {ref, onMounted} from 'vue'
import { useRoute } from "vue-router";
 
const { params } = useRoute();
const announcementId = params.id;

const selectedAnnouncement = ref({
  announcementTitle: "",
  categoryId: "",
  announcementDescription: "",
  publishDate: "",
  closeDate: "",
  announcementDisplay: ""
});

onMounted(async() => {
    const Announcement = await getIdAnnouncement(announcementId);
    if (Announcement) {
    selectedAnnouncement.value = {
      announcementTitle: Announcement.announcementTitle,
      categoryId: Announcement.categoryId,
      announcementDescription: Announcement.announcementDescription,
      publishDate: convertToDatabaseTime(Announcement.publishDate),
      closeDate: convertToDatabaseTime(Announcement.closeDate),
      announcementDisplay: Announcement.announcementDisplay,
    };
    console.log("fetched successfully");

  }
  
})
function convertToDatabaseTime(dateString) {

  const date = new Date(dateString);
  const formattedDate = date.toISOString();

  return formattedDate;
}

async function updateAnnouncements() {
    try {
    if (announcementId !== null) {
      selectedAnnouncement.value.publishDate = convertToDatabaseTime(selectedAnnouncement.value.publishDate);
      selectedAnnouncement.value.closeDate = convertToDatabaseTime(selectedAnnouncement.value.closeDate);
    
      await updateAnnouncement(selectedAnnouncement.value, announcementId);
    }
  } catch (error) {
    console.log("Failed to update announcement:", error);
  }
}

</script>
 
<template>
    <div class="page">
        <div class="Announcements">
        <h1>Announcement Detail:</h1>
    </div>
        <div class="data">
                <p class="fontbold">Title</p>
                <input type="text" class="ann-title" v-model="selectedAnnouncement.announcementTitle">
            <div>
                <p class="fontbold">Category</p>
                <select class="ann-category" v-model="selectedAnnouncement.categoryId">
                    <option value="1">ทั่วไป</option>
                    <option value="2">ทุนการศึกษา</option>
                    <option value="3">หางาน</option>
                    <option value="4">ฝึกงาน</option>
                </select>
            </div>
                <p class="fontbold">Description</p>
                <input  type="text" class=" ann-description" v-model="selectedAnnouncement.announcementDescription">

                <p class="fontbold">Publish Date</p>
                <input  type="datetime-local" class=" borderstyle " v-model="selectedAnnouncement.publishDate">

                <p class="fontbold">Close Date</p>
                <input  type="datetime-local" class=" borderstyle" v-model="selectedAnnouncement.closeDate">

                <p class="fontbold">Display</p>

                <div class="ann-radio-display">
                    <input type="radio" id="Y" class=" ann-display" value="Y"
                        v-model="selectedAnnouncement.announcementDisplay">
                    <label for="Y">Y</label>
                    
                    <input type="radio" id="N" class="ann-display" value="N"
                        v-model="selectedAnnouncement.announcementDisplay">
                    <label for="N">N</label>
                </div>
                
        </div>
        <div class="Btn">
            <button class="ann-button-done" @click="() => {
                updateAnnouncements()
                $router.go(-1)
                }">done</button>
            <button class="ann-button-cancel" @click="$router.go(-1)">cancel</button>
        </div>
    </div>

</template>

<style scoped>
input[type=text], select {
    border: 1px solid #cacaca;
    border-radius: 0.4rem;
    width: 60%;
    padding: 6px;
}
.data{
    border: 1px solid #cacaca;
    width: 90%;
    padding: 20px;
    margin: 10px;
}
.fontbold{
    font-weight: bold;
    padding: 5px 20px;
    
}
.page{
    margin-top: 50px;
    margin-left: 70px;
}
h1 {
    text-align: left; 
    font-size: 30px; 
    font-weight: bold;
    padding-bottom: 10px;
  }
.ann-category{
  border: 1px solid #cacaca; 
  border-radius: 0.4rem;
  align-items: center;
  width: 30%;
}
.Btn{
    padding: 5px 20px;
}
.ann-button-done{
    margin: 0 auto;
    margin-bottom: 5px;
    width: fit-content;
    background-color: #75dc81;
    border-radius: 0.5rem;
    border: 10px solid  #75dc81;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
}
.ann-button-done:hover {
    background-color: rgb(92, 160, 52);
    border-color: rgb(92, 160, 52);
    color: #ffffff;
}
.ann-button-cancel{
  margin: 0 auto;
    margin-bottom: 5px;
    width: fit-content;
    background-color: #ffa0a0;
    border-radius: 0.5rem;
    border: 10px solid #ffa0a0;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
    margin-left: 20px;
}
.ann-button-cancel:hover {
    background-color: #ff5858;
    border-color: #ff5858;
    color: #ffffff;
}

.ann-display {
    margin: 0px 10px;
}

.borderstyle{
  border: 1px solid #cacaca; 
  border-radius: 0.3rem;
  padding: 5px;
}

</style>
