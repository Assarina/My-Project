<script setup>
import { ref } from "vue"
import ResultWindow from "./component/ResultWindow.vue"
import GamePlay from "./component/GamePlay.vue"
import Nav from "./component/Nav.vue"
import { usePage } from "./stores/page.js"
import Username from "./component/Username.vue"
import { usePlayer } from "./stores/player.js"
import { useAPI } from "./stores/myAPI.js"

// Variable declaration
const myPlayer = usePlayer()
const myAPI = useAPI()
const point = ref(0)
const botPoint = ref(0)
let result = ref("Click on any choice to start!")
const myPage = usePage()


// Some Function

// Player Point

function playerWin() {
  point.value++
  if (botPoint.value === 0) botPoint.value = 0
  else botPoint.value--
  if (point.value === 5) {
    addOrEdit()
  }
}

function playerLose() {
  botPoint.value++
  if (point.value === 0) point.value = 0
  else point.value--
}



//---------------------------   Game Function   -------------------------

async function addOrEdit() {
  const selectedId = ref(0)
  const selectedWin = ref(0)
  for (let data of await myAPI.datas) { //for-of ไปลูปใน db.json ออกมา เพื่อเอามาตรวจสอบว่าตรงในเงื่อนไข if มั้ย
    if (data.name === myPlayer.username) { //ถ้าตรงกับอันนี้ จะเอา id กับ win ไปเก็บไว้ใน selectedid, selectedwin เพื่อเก็บเอาไว้ใช้ต่อ
      selectedId.value = data.id
      selectedWin.value = data.win
    }
  }
  selectedId.value === 0 
  ? await myAPI.addData(myPlayer.username, selectedWin.value + 1) + console.log('Added new player') 
  : await myAPI.editData(selectedId.value, myPlayer.username, selectedWin.value + 1) + console.log("Updated player's profile")
  selectedId.value = 0
  selectedWin.value = 0
}

const arr = ["Rock", "Paper", "Scissor"]
let Bot = ""

function rock() {
  if (point.value !== 5 && botPoint.value !== 5) {
    Bot = arr[Math.floor(Math.random() * 3)]
    if (Bot === "Rock") {
      result.value = "Tied"
    } if (Bot === "Paper") {
      result.value = "Lose"
      playerLose()
    } if (Bot === "Scissor") {
      result.value = "Win"
      playerWin()
    }
  }
}

function paper() {
  if (point.value !== 5 && botPoint.value !== 5) {
    Bot = arr[Math.floor(Math.random() * 3)]
    if (Bot === "Rock") {
      result.value = "Win"
      playerWin()
    } if (Bot === "Paper") {
      result.value = "Tied"
    } if (Bot === "Scissor") {
      result.value = "Lose"
      playerLose()
    }
  }
}

function scissor() {
  if (point.value !== 5 && botPoint.value !== 5) {
    Bot = arr[Math.floor(Math.random() * 3)]
    if (Bot === "Rock") {
      result.value = "Lose"
      playerLose()
    } if (Bot === "Paper") {
      result.value = "Win"
      playerWin()
    } if (Bot === "Scissor") {
      result.value = "Tied"
    }
  }
}

function reset() {
  botPoint.value = 0
  point.value = 0
  Bot = ""
  result.value = "Click on any choice to start!"
}

// Music
const bgMusic = ref('bgMusic.mp3')
const myAudio = ref('true')
const isPlaying = ref(false)
const playStopMusic = () => {
  isPlaying.value = !isPlaying.value
  if (isPlaying.value) {
    myAudio.value.play()
  } else {
    myAudio.value.pause()
  }
}
const showRules = ref(false)

</script>

<template>
  <div id="window">
    <div class="w-full h-screen grid grid-cols-3 grid-rows-3 overflow-hidden">
      <Username v-if="myPage.page === 'login'"></Username>
      <!------------------Menu Interface---------------------->


      <Nav class="col-span-3 col-start-1 row-start-1" v-if="myPage.page === 'mainmenu'"></Nav>

      <!------- Music ------->
      <div>
        <audio :src="bgMusic" ref="myAudio"></audio>
        <button @click="playStopMusic" class="w-20 absolute bottom-2 left-3 hover:scale-110">
          <img :src="isPlaying ? '/SoundOn.png' : '/SoundOff.png'" />
        </button>
      </div>


      <button class="w-16 absolute bottom-3 right-3 hover:scale-110">
        <img src="./assets/elements/Rules.png" @click="showRules = !showRules" />
      </button>
      <div class="w-full h-full col-start-3 row-start-3 text-center">
        <div class="w-full h-full grid grid-cols-3 grid-rows-3" v-if="showRules">
      <img src="./assets/elements/RulesDetails.png" class="col-start-1 row-start-1 col-span-2"/>
          <span class="close-btn justify-self-end w-5 h-5 col-start-2 row-start-1" @click="showRules = false">&times;</span>
        </div>
      </div>



      <!-----------Gameplay Interface------------->

      <GamePlay class="col-start-1 row-start-1 col-span-3 row-span-3" v-if="myPage.page === 'GamePlay'">
        <template v-slot:BackBtn>
          <img src="./assets/elements/back.png " @click="() => {
            myPage.page = 'mainmenu'
            reset()
          }" />

        </template>
        <template v-slot:BotChoose>
          <div class="grid grid-cols-3 items-center">
            <img src="./assets/elements/computer.png" class="col-start-1 row-start-1 col-span-3" />
            <h1 class="result col-start-3 row-start-1"> {{ Bot }}</h1>
          </div>
        </template>


        <template v-slot:Player>
          <div> <!-- Player plate -->
            <table class="tablestyle w-full h-24">
              <tr>
                <th>{{ myPlayer.username }}</th>
              </tr>
              <tr>
                <td>{{ point }}</td>
              </tr>
            </table>
          </div>
        </template>

        <template v-slot:GameResult>
          <h1 class="text-4xl">{{ result }}</h1> <!-- Result of the game -->
        </template>

        <template v-slot:Bot>
          <div> <!-- Bot plate -->
            <table class="tablestyle w-full h-24">
              <tr>
                <th>Bot</th>
              </tr>
              <tr>
                <td> {{ botPoint }}</td>
              </tr>
            </table>
          </div>
        </template>
        <!-- 
              <template v-slot:Choice1 class="justify-center w-2/4 pb-5 mx-auto hover:animate-bounce" >
          
                <img src="./assets/elements/rockandborder.png" @click="rock" 
                class="justify-center mt-16 w-2/4 pb-5 mx-auto hover:animate-bounce" />
                <img src="./assets/elements/paperandborder.png"  @click="paper" 
                class="justify-center mt-16 w-2/4 pb-5 mx-auto hover:animate-bounce" />
                <img src="./assets/elements/scissorandborder.png" @click="scissor"
                class="justify-center mt-20 w-2/4 pb-5 mx-auto hover:animate-bounce" />

          
              </template> -->

        <template v-slot:Choice2 class="justify-center w-2/4 pb-5 mx-auto hover:animate-bounce">
          <img src="./assets/elements/rock1.png" @click="rock"
            class="justify-centermg w-2/4 pb-5 mx-auto cursor-pointer hover:scale-110 transition duration-300 ease-in-out" />
          <img src="./assets/elements/paper1.png" @click="paper"
            class="justify-centermg w-2/4 pb-5 mx-auto cursor-pointer hover:scale-110 transition duration-300 ease-in-out" />
          <img src="./assets/elements/scissor1.png" @click="scissor"
            class="justify-centermg w-2/4 pb-5 mx-auto cursor-pointer hover:scale-110 transition duration-300 ease-in-out" />
        </template>
      </GamePlay>


      <ResultWindow class="col-start-1 row-start-1 col-span-3 row-span-3" :point="point" :botPoint="botPoint" v-if="point === 5 || botPoint === 5" @reset="reset" />
    </div>
  </div>
</template>

<style scoped>
/* css */


#window {
  background-image: url(./assets/elements/Wallpaper1.png);
  background-size: cover;
  background-repeat: repeat-y;

}

#Pscore {
  position: absolute;
  margin-left: 66.5%;
  margin-top: 10px;
  font-size: 40px;
}

#Bscore {
  position: absolute;
  margin-left: 16.5%;
  margin-top: 10px;
  font-size: 40px;
}

#Choose {
  position: absolute;
  margin-left: 59%;
  font-size: 50px;
  margin-top: -0.5%;
  font-weight: bolder;
}

.backbutton {
  margin-top: -420px;
  width: 90px;
  right: 400px;
}

#popup {
  position: absolute;
  width: 50%;
  height: 50%;
  margin-left: 25%;
  bottom: 25%;
  border-radius: 45px;
  box-shadow: 10px 10px 2px grey;
}

#total {
  position: absolute;
  margin-left: 16.5%;
  margin-top: 55px;
  font-size: 40px;
}

้.score {
  position: absolute;
  margin-left: 25%;
}

/* #mo{  
  position: absolute;
  margin-top: 200px;
} */
.tablestyle {
  background-color: white;
  border-collapse: collapse;
  /* min-width: 250px;
    min-height: 100px; */
  border-radius: 15px;
}

.tablestyle th {
  background-color: rgb(240, 44, 135);
  color: white;
  overflow: hidden;
  border-top-right-radius: 15px;
  border-top-left-radius: 15px;
}

.result {
  font-size: 40px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 800;
}

.close-btn {
  background: #e62e2e;
  color: #eee;
  line-height: 15px;
  border-radius: 14px;
  cursor: pointer;
}
</style>