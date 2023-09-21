<script setup>
const props = defineProps({
    point : {
        type : Number,
        require : true
    },
    botPoint : {
        type : Number,
        require : true
    }
})

const emits = defineEmits(['reset'])

</script>

<template>
    <div class="justify-center w-full ">
        <div id="popup" class="text-center bg-white ">
            <h1 class="scale-125  text-green-600 font-extrabold text-6xl  font-serif  mx-auto mt-20 ">{{ point === 5 ? "YOU WIN!":"BOT WIN!" }}</h1>
            <img class="scale-75 hover:opacity-70 mx-auto mt-16 " src="../assets/elements/playagain.png" @click="$emit('reset')">
        </div>
    </div>
</template>

<style scoped>
#popup {
  position: absolute;
  width: 50%;
  height: 50%;
  margin-left: 25%;
  bottom: 25%;
  border-radius: 45px;
  box-shadow: 10px 10px 2px grey;
}</style>