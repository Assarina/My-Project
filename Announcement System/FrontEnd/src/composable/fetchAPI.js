import { ref } from "vue";

const url = "http://localhost:8080/api"

// const url = import.meta.env.VITE_API_ROOT

const datas = ref ({})
async function  getAnnouncements() {
    try {
        const res = await fetch(url + "/announcements")
        if (res.ok) {
            datas.value = await res.json()
            console.log(datas.value)
            return datas.value
        } else {
            console.log("Can not getAnnouncements")
        }
    } 
    catch (err) {
        console.log(err)
    }
}
  
  async function getIdAnnouncement(id) {
    try {
      const res = await fetch(url + "/announcements/" + id)
      if (res.ok) {
        datas.value = await res.json()
        return datas.value

      } else if (res.status === 404) {

      } else {
        console.log("Can not getAnnouncements")
      }
    } catch (err) {
      console.log(err)
    }
  }

//----------------------Sprint 2------------------------------------

  async function addAnnounce(announce) {
      try {
          const res = await fetch(url + "/announcements", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(announce)
          });

          if (res.ok) {
            console.log("AddNewAnnounce!!!")
          } else {
              console.log("Can not AddNewAnnounce")
          }
      } 
      catch (err) {
          console.log(err)
      }
  }
  
  async function deleteAnnouncement(id) {
    try {
      const res = await fetch(url + "/announcements/" + id, {
        method: 'DELETE'
      });

      if (res.ok) {
        datas.value = datas.value.filter((data) => data.id !== id); 
      } else {
        throw new Error("Can not delete Annoucements");
      }
    } catch (err) {
      console.log(err);
    }
  }

  async function updateAnnouncement(updatedAnnouncement, id) {
    try {
      const res = await fetch(url + "/announcements/" + id , {
        method: "PUT",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedAnnouncement)
      });
      if (res.ok) {
        console.log("Updated!!!");
      } else {
        console.log("Cannot update announcement");
      }
    } catch (err) {
      console.log(err);
    }
  }

  //----------------------Sprint 3------------------------------------

export { getAnnouncements , getIdAnnouncement , addAnnounce , deleteAnnouncement, updateAnnouncement}
