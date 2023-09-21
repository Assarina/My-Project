<script setup>
import { ref } from "vue";
import { onMounted } from "vue";
import { getData } from "../composable/getData";
import { useAPI } from "../stores/myAPI"

const myAPI = useAPI()
const datas = ref([])
const addMode = ref(false)
const editMode = ref(false)
const username = ref('')
const selectedId = ref(0)
const selectedWin = ref(0)

async function addOrEdit() {
    for (let data of datas.value) {
        if (data.name === username.value) {
            selectedId.value = data.id
        }
    }
    selectedId.value === 0 
    ? await myAPI.addData(username.value, Number(selectedWin.value)) //ถ้าเท่ากับ 0 ให้ add ใหม่
    : await myAPI.editData(selectedId.value, username.value, Number(selectedWin.value))
    datas.value = await myAPI.datas
    username.value = ''
    selectedId.value = 0
    selectedWin.value = 0
}

async function deleteData(id) {
    await myAPI.deleteData(id)
    datas.value = await myAPI.datas
}

onMounted(async () => {
    datas.value = await getData()
    console.log(datas.value)
}
)

</script>

<template>
    <div class="grid grid-cols-3 grid-flow-row justify-center">
        <div class="col-start-2 row-start-1 pt-2">
            <h1 class="px-2">Score Board</h1>
        </div>
        <div class="tablestyle w-screen col-span-3 col-start-1 row-start-2 grid grid-flow-row row-end-2">
            <div class="thead grid grid-cols-4">
                <p class="th">Place</p>
                <p class="th">Player</p>
                <p class="th">Wins</p>
                <p class="th">Edits</p>
            </div>
            <div class="tbody row-start-2 row-end-2 h-52 overflow-scroll">
                <div class="grid grid-cols-4" v-for="(data, index) in datas">
                    <p class="td" :class="'row-start-' + index">{{ index + 1 }}</p>
                    <p class="td" :class="'row-start-' + index">{{ data.name }}</p>
                    <p class="td" :class="'row-start-' + index">{{ data.win }}</p>
                    <div  class="td" :class="'row-start-' + index">
                        <button
                            @click="editMode = !editMode, username = data.name, selectedId = data.id, selectedWin = Number(data.win)"
                            v-if="!editMode">Edit</button>
                        <button class="ml-5" @click="deleteData(data.id)"><span style="color: red;">Delete</span></button>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-start-2 row-start-3" v-if="addMode || editMode">
            <input v-model="username" /><br>
            <input v-model="selectedWin" /><br>
            <button @click="addOrEdit(), editMode = false, addMode = false">Confirm</button>
            <button class="ml-5"
                @click="username = '', selectedId = 0, selectedWin = 0, editMode = false, addMode = false">Cancel</button>
        </div>
        <!-- <h1 class="bg-white rounded-lg mt-2 pb-1.5">{{ selectedId }}</h1> -->
        <div class="col-start-2 row-start-3" v-if="!addMode && !editMode">
            <!-- <button >Return to Main Menu</button> -->
            <button class="ml-12" @click="addMode = !addMode">Add</button>
        </div>
    </div>
</template>
 
<style scoped>
.tablestyle {
    background-color: white;
    border-collapse: collapse;
    margin-top: 5px;
}

.tablestyle .thead .th {
    background-color: rgb(255, 216, 145);
    color: white;
    padding: 10px;
    overflow: hidden;
}

.tablestyle .tbody .td {
    border-bottom: 1px solid #dddddd;
}

input,
button {
    border: 1px solid #dddddd;
    background-color: white;
    border-radius: 8px;
    margin-top: 5px;
    padding: 0px 5px 5px 5px;
}

button:hover {
    background-color: #dfdfdf;
}

h1 {
    font-size: 28px;
    font-weight: bold;
    display: inline-block;
    background-color: #f49369;
    border-radius: 8px;
}
</style>