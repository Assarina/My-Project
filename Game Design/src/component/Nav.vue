<script setup>
import { RouterLink } from 'vue-router'
</script>
 
<template>
<div class="text-center pt-5 ">
    <RouterLink :to="{name : 'MainMenu'}" active-class="active" class="nav-link">Home</RouterLink> 
    |
    <RouterLink :to="{name : 'AboutUs'}" active-class="active" class="nav-link">About Us</RouterLink>
    |
    <RouterLink :to="{name : 'ScoreBoard'}" active-class="active" class="nav-link">Score Board</RouterLink>
    <router-view></router-view> <!-- เรียกหน้าของ mainmenu -->
</div>
</template>
 
<style scoped>
.nav-link.active{
    background-color: rgb(255, 207, 207);
}
.nav-link{
    color: black;
    text-decoration: none;
    padding: 0.25rem;
    transition: background-color 0.3s ease-in-out;
    border-radius: 10px;
}
.nav-link:hover{
    background-color: rgb(255, 255, 255);
    border-radius: 10px;
}
</style>