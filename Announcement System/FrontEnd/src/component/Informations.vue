<script setup>
 import { getIdAnnouncement } from "../composable/fetchAPI.js";
 import { onMounted,ref } from "vue";
 import { useRoute, useRouter } from "vue-router";
 
const { params } = useRoute();
const router = useRouter();
const announcementId = params.id;
const category = ref([]);

onMounted(async () => {
    category.value = await getIdAnnouncement(announcementId);
    if (!category.value) {
      alert(`The requested page is not available!`);
      router.push("/admin/announcement");
    }
  });

const options = { 
      day: 'numeric', 
      month: 'short', 
      year: 'numeric', 
      hour: 'numeric', 
      minute: '2-digit',  
      hour12: false
    };

//useRoute
 const BackHomePage =()=>{
  router.push("/admin/announcement")
}

</script>

<template>
    <div class="page">
        <h1>Announcement Detail:</h1> 
    <div>
      <table v-if="category">
        <tr>
          <td class="fontbold">Title</td>
          <td>{{ category.announcementTitle }}</td>
        </tr>
        <tr>
            <td class="fontbold">Category</td>
          <td>{{ category.announcementCategory }}</td>
        </tr>
        <tr>
            <td class="fontbold">Description</td>
          <td>{{ category.announcementDescription }}</td>
        </tr>
        <tr>
            <td class="fontbold">Publish Date</td>
          <td>{{ category.publishDate ? new Date(category.publishDate).toLocaleString("en-GB", options) : '-' }} </td>
        </tr>
        <tr>
            <td class="fontbold">Close Date</td>
          <td>{{ category.closeDate ? new Date(category.closeDate).toLocaleString("en-GB", options) : '-' }}</td>
        </tr>
        <tr>
            <td class="fontbold">Display</td>
          <td>{{  category.announcementDisplay }}</td>
        </tr>
      </table>

      <div class="ml-12">

        <button class="Btn mr-14" @click="() => router.push(`/admin/announcement/${announcementId}/edit`)">Edit</button>

        <button class="Btn" @click="BackHomePage()">Back</button>

        </div>

      </div>
    </div>
  </template>


<style scoped>

h1 {
    text-align: left; 
    font-size: 30px; 
    font-weight: bold;
  }
  .Btn{
    margin-top: 10px;
    margin-left: -50px;
    background-color: #cacaca;
    border-radius: 0.5rem;
    border: 10px solid #cacaca;
    padding: 0.01rem 1.0rem;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }
  .Btn:hover {
    background-color: #555555;
    border-color: #555555;
    color: #ffffff;
}

  table {
  border-collapse: collapse;
  border: 1px solid #cacaca;
  width: 90%;
}

td {
  padding: 20px;
}


.fontbold{
    font-weight: bold;
}

.page{
    margin-top: 50px;
    margin-left: 70px;
}


</style>

