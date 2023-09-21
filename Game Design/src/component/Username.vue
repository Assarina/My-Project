<script setup>
import { usePage } from "../stores/page.js";
import { usePlayer } from "../stores/player.js";
import { ref } from "vue";

const myPage = usePage();
const myPlayer = usePlayer();
const username = ref("");

const login = async () => {
  if (username.value !== "") {
    await myPlayer.setUsername(username.value);
    myPage.setPage("GamePlay");
  } else {
    alert("Please, Enter your name before start game!");
  }
};
</script>

<template>
  <div id="login">
    <div class="close-btn pl-1" @click="() => (myPage.page = 'mainmenu')">
      &times; //ปุ่มกากบาท
    </div>
    <div class="from-element">
      <h1 class="font-extrabold font-serif">
        Welcome to <br />
        Rock Paper Scissor!
      </h1>
    </div>
    <div class="from-element">
      <label>Username</label>
      <input
        type="text"
        name="username"
        v-model="username"
        placeholder="Enter your name"
      />
    </div>
    <div class="from-element">
      <button type="button" v-on:click="login()">Start game</button>
    </div>
  </div>
</template>

<style scoped>
#login {
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 30%;
  padding: 20px 30px;
  border-radius: 10px;
  z-index: 999;
  /* box-shadow: 2px 2px 5px 5px rgb(141, 141, 141); */
}
.from-element {
  margin: 15px 0px;
}
h1 {
  text-align: center;
  color: #222;
  font-size: 20px;
  margin: 10px 0px 20px;
}
input[type="text"] {
  display: block;
  margin-top: 10px;
  width: 100%;
  height: 35px;
  padding: 20px;
  outline: none;
  border: 1px solid #aaa;
  border-radius: 5px;
}
.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 20px;
  height: 20px;
  background: #e62e2e;
  color: #eee;
  line-height: 15px;
  border-radius: 10px;
  cursor: pointer;
}
button {
  width: 100%;
  height: 50px;
  border: none;
  outline: none;
  font-size: 16px;
  background: rgb(173, 173, 173);
  color: white;
  border-radius: 10px;
  cursor: pointer;
  margin-top: 30px;
}
button:hover {
  background: rgb(143, 143, 143);
  color: white;
}
</style>
