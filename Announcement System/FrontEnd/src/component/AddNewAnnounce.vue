<script setup>

 import { addAnnounce } from "../composable/fetchAPI.js";
 import { ref, computed} from "vue";
 import { useRouter } from "vue-router";

const router = useRouter();
const selectedCategoryId = "1";

 const title = ref('')
 const  categoryId = ref(selectedCategoryId)
 const description = ref('')
 const publishDate = ref('')
 const closeDate = ref('')
 const display = ref(false)

 const convertToDatabaseTime = (localTimeString) => {
  const localTime = new Date(localTimeString);
  const databaseTime = localTime.toISOString(); // แปลงให้อยู่ในรูปแบบ ISO 8601

  return databaseTime;
};
// submit form
const submitForm = async () => {
  try {
    const announce = {
      announcementTitle: title.value,
      announcementDescription: description.value,
      publishDate: publishDate.value ? convertToDatabaseTime(publishDate.value) : null,
      closeDate: closeDate.value ? convertToDatabaseTime(closeDate.value) : null,
      announcementDisplay: display.value ? 'Y' : 'N',
      categoryId: categoryId.value
    };
    await addAnnounce(announce);
    BackHomePage();
  } catch (err) {
    console.log(err);
  }
};

 // useRoute
const BackHomePage = () => {
  router.push("/admin/announcement");
};

// Title limit at 200
const TitleLimit = computed(() => {
  return title.value.length > 199;
});

// Test Count
const TitleCount = computed(() => {
  return title.value.length;
});

// Description limit at 200
const DescriptLimit = computed(() => {
  return description.value.length > 9999;
});

// Test Count Description
const DescriptCount = computed(() => {
  return description.value.length;
});

</script>

<template>
    <div class ='page'>

    <form @submit.prevent="submitForm">
       <div id= "style" style="padding: 20px; padding-top: 0;">
        <div>

        <h1>Announcement Detail:</h1> 

        <p>Title</p>
          <input 
          v-model="title" 
          maxlength="200"
          :class="{'p-1': true, 'exceeded-limit': TitleLimit}"
          style="border: 1px solid #cacaca; border-radius: 0.2rem; width: 100%;" 
          placeholder="Please enter title" />
          <p> {{ TitleCount }} / 200 characters</p>
          <span v-if="TitleLimit" class="character-limit">Characters limit at 200 characters</span>
          </div>

        <div>
          <p>Category</p>
            <select  class="borderstyleCategory" v-model=" categoryId">
              <option value="1">ทั่วไป</option>
              <option value="2">ทุนการศึกษา</option>
              <option value="3">หางาน</option>
              <option value="4">ฝึกงาน</option>
            </select>
        </div>

        <div>
          <p >Description:</p>
              <textarea 
              v-model="description" 
              maxlength="10000"
              :class="{'p-1': true, 'exceeded-limit': DescriptLimit}"
              style=" border: 1px solid #cacaca; border-radius: 0.2rem; width: 100%;"  
              placeholder=" Please Enter Description" >
        </textarea>
        <p> {{ DescriptCount }} / 10000 characters</p>
        <span v-if="DescriptLimit" class="character-limit">Characters limit at 10000 characters</span>
        </div>

        <div>
          <p>Publish Date</p>
              <input type="datetime-local" class="borderstyle" v-model="publishDate" />&nbsp;
              <!-- <input type="time" class="borderstyle" v-model="publishTime" /> -->
        </div>

        <div>
          <p>Close Date</p>
          <input type="datetime-local" class="borderstyle" v-model="closeDate" />&nbsp;
          <!-- <input type="time" class="borderstyle" v-model="closeTime" /> -->
        </div>

        <div>
          <p>Display</p>
            <input type="checkbox" 
            id="checkbox"
            v-model="display">
            <label for="checkbox"> Check to show this announcement:</label>
        </div>

        <div>
          <button type="Submit" class="SubmitBtn">Submit</button>

          <button class="CancelBtn" @click="BackHomePage()">Cancel</button>

        </div>

       </div>
    </form>

</div>
</template>

<style scoped>
form{
  border-collapse: collapse;
  border: 1px solid #cacaca;
  border-radius: 0.5rem;
  width: 90%;
}
div{
  padding-top: 15px;
}
.page {

    margin-left: 70px;
  }
h1 {
    text-align: left; 
    font-size: 30px; 
    font-weight: bold;
    padding-bottom: 10px;
  }
.SubmitBtn{
    margin: 0 auto;
    margin-bottom: 5px;
    width: fit-content;
    background-color: #75dc81;
    border-radius: 0.5rem;
    border: 10px solid  #75dc81;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
  }

  .SubmitBtn:hover {
    background-color: rgb(92, 160, 52);
    border-color: rgb(92, 160, 52);
    color: #ffffff;
}
.CancelBtn{
  margin: 0 auto;
    margin-bottom: 5px;
    width: fit-content;
    background-color: #ffa0a0;
    border-radius: 0.5rem;
    border: 10px solid #ffa0a0;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
    margin-left: 20px;
  }
  .CancelBtn:hover {
    background-color: #ff5858;
    border-color: #ff5858;
    color: #ffffff;
}
.borderstyle{
  border: 1px solid #cacaca; 
  border-radius: 0.3rem;
  padding: 5px;
}
.borderstyleCategory{
  border: 1px solid #cacaca; 
  border-radius: 0.5rem;
  align-items: center;
  padding: 2px;
}
.exceeded-limit {
  border-color: red;
}

.character-limit {
  color: red;
  font-size: 12px;
}
</style>
