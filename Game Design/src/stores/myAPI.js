import { defineStore } from "pinia";
import { acceptHMRUpdate } from "pinia";
import { getData } from "../composable/getData";
import { ref } from "vue";

export const useAPI = defineStore('API', () => {
    const datas = ref([])
    datas.value = getData()
    async function addData(name, win) {
        try {
            const res = await fetch('http://localhost:5000/scoreBoard', {
                method: "POST", //เอาข้อมูลใน body ไป create ใหม่ ส่งไปให้ backend
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    name: name,
                    win: win
                })
            })
            if (res.ok) {
                datas.value = await getData()
            } else {
                console.log("Can not add the data")
            }
        } catch (err) {
            console.log(err)
        }
    }
    async function editData(id, name, win) {
        try {
            const res = await fetch('http://localhost:5000/scoreBoard/' + id, { 
                method: 'PUT', //update
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    name: name,
                    win: win
                })
            })
            if (res.ok) {
                datas.value = await getData()
            } else {
                console.log("Can not edit this data")
            }
        } catch (err) {
            console.log(err)
        }
    }
    async function deleteData(id) {
        try {
            const res = await fetch('http://localhost:5000/scoreBoard/' + id, {
                method: "DELETE"
            })
            if (res.ok) {
                datas.value = await getData()
            } else {
                console.log("Can not delete the data")
            }
        } catch (err) {
            console.log(err)
        }
    }
    return {datas,addData,editData,deleteData}
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useAPI, import.meta.hot))
}