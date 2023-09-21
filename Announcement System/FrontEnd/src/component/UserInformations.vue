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
      router.push("/announcement");
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
  router.push("/announcement")
}
</script>

<template>
    <div class="page">
      
    <div>
      <table>
        <tr>
             <div class="Title">
          <h1>
              {{ category.announcementTitle }}
          </h1>
          <h2>
            <span v-if="category.closeDate"  class="closed-label">Closed on:</span>
            <span v-if="category.closeDate"  class="closed-date">{{ category.closeDate ? new Date(category.closeDate).toLocaleString("en-GB", options) : '-' }}</span>
          </h2>
             </div>
           <p>
            <span class="Category">{{ category.announcementCategory }}</span> 
          </p>
        </tr>

        <tr>
          <p>
            <span class="Description">{{ category.announcementDescription }}</span>
            </p>
        </tr>

 
          <button class="Btn" @click="BackHomePage()">Back</button>

      </table>


      </div>
    </div>
  </template>


<style scoped>
.Title{
  padding-top: 10px;
  padding-left: 10px;
  display: flex;
  justify-content: space-between;
}
  h1 {
    text-align: left ; 
    font-size: 25px; 
    font-weight: bold;
    font-family: 'Montserrat', sans-serif;
  }
  h2{
    display: flex;
    padding-right: 20px;
  }
  .closed-label {
  color: red;
  font-weight: bold;
  padding-right: 20px;
}

.closed-date {
 color: #bcbcbc;
}
  p{
    display: flex;
  }
  .Category{
    color: #9b9b9b;
    padding-left: 10px;
    padding-bottom: 10px;
  }
  .Description{
    padding: 10px;
  }
  .Btn{
    margin: 10px;
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
  border-collapse: separate;
  border: 1px solid #cacaca;
  width: 90%;
  border-radius: 5px;
  border-spacing: 0;
}

tr{
    border-bottom: 1px solid #ddd;
  }


.fontbold{
    font-weight: bold;
}

.page{
    margin-top: 50px;
    margin-left: 70px;
}


</style>