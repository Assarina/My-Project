<script setup>
import { usePage } from '../stores/page.js';
const myPage = usePage()


</script>
 
<template>
  <div class="w-screen h-screen grid grid-cols-6 grid-flow-row justify-center justify-items-center"> <!-- create 3x3 grid. Technically, 3 x (infinite) grid -->
    <div class="col-span-4 col-start-2 row-start-1 grid grid-cols-5"> <!-- col 2, row 1 -->
      <img src="../assets/elements/Logo.png" class="col-start-2 col-span-3"/>
    </div>
    <div class="col-span-2 col-start-3 row-start-2"> <!-- col 2, row 2 -->
      <img src="../assets/elements/Start.png" class="cursor-pointer hover:scale-110 transition duration-300 ease-in-out"
        @click="myPage.setPage('login')" />
    </div>
    <div class="col-span-4 col-start-2 row-start-3"> <!-- col 2, row 3 -->
      <div class="grid grid-cols-5"> <!-- grid for each items -->
        <img src="../assets/elements/rock2.png" class="col-start-1">
      <img src="../assets/elements/paper2.png" class="col-start-3">
      <img src="../assets/elements/scissor2.png" class="col-start-5">
    </div>
  </div>
</div>

<!--      <div class="w-full justify-center">

 <div class="scale-50 -mt-20 ">
    <img src="../assets/elements/Logo.png"/>
  </div>
  <div class="justify-center w-2/5 pb-5 -mt-20  mx-auto cursor-pointer hover:scale-110 transition duration-300 ease-in-out">
    <img src="../assets/elements/Start.png" @click="myPage.setPage ('login')" />
  </div>
  <div class="columns-3 scale-50 ">
    <img src="../assets/elements/rockandborder.png" >
    <img src="../assets/elements/paperandborder.png" >
    <img src="../assets/elements/scissorandborder.png" >
  </div> 
  
            <div class="columns-3 ml-72    w-2/4   -mt-4 scale-100  ">
              <img src="../assets/elements/rock2.png" class=" ml-24 "    >
              <img src="../assets/elements/paper2.png" class="ml-24" >
              <img src="../assets/elements/scissor2.png" class="ml-24" >
            </div>
          </div> -->
</template>
 
<style scoped></style>