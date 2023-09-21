<script>

</script>
 
<template>
    <div class="h-screen w-screen grid grid-cols-5 grid-flow-row items-center">
        <!-- <div class = "rounded-lg bg-white p-10 text-2xl font-bold pt-10  "> -->
        <div class="details col-span-3 col-start-2 row-start-1">
            <p>Hello! We are just a group of student who create this web game!<br>
                If you like it, please consider recommend to your friends. That would help us a lot!<br>
                Also, thank you for playing our game. Have a good time!</p>
        </div>
        <div class="col-span-3 col-start-2 row-start-2">
            <div class="grid grid-cols-5">
                <img src="../assets/elements/rock2.png" class="col-start-1">
                <img src="../assets/elements/paper2.png" class="col-start-3">
                <img src="../assets/elements/scissor2.png" class="col-start-5">
            </div>
        </div>
    </div>
</template>
 
<style scoped>
.details {
    display: inline-block;
    background-color: white;
    font-size: larger;
    font-weight: bold;
    padding: 40px;
    border-radius: 20px;
}
</style>