<script>

</script>
 
<template>
    <div>
        <div class=" w-24 absolute right-2 hover:scale-110 transition duration-300 ease-in-out">
            <slot name="BackBtn"></slot>
        </div>
        <div class="w-screen grid grid-cols-3 grid-flow-row">
            <div class="col-start-1 col-span-3 grid grid-cols-5 mt-5">
                <div class="w-2/4 justify-self-end self-end">
                    <img src="../assets/elements/PinkWink.png" class="animate-bounce" />
                </div>
                <div class="col-span-3">
                    <slot name="BotChoose"></slot>
                </div>
                <div class="w-2/4 justify-self-start self-end">
                    <img src="../assets/elements/YellowWink.png" class="animate-bounce " />
                </div>
            </div>
        </div>

        <div class="w-screen flex mt-5 justify-center text-center">
            <div class="w-1/4">
                <slot name="Player"></slot>
            </div>
            <div class="w-1/3 shrink-0">
                <slot name="GameResult"></slot>
            </div>
            <div class="w-1/4">
                    <slot name="Bot"></slot>
                </div>
        </div>
        <div class="grid grid-cols-3 justify-between scale-100 mt-6">
            <slot name="Choice2"></slot>
        </div>
    </div>

    <!-- <div class="w-full h-screen justify-center">
        <div class=" w-24 absolute right-2 hover:scale-110 transition duration-300 ease-in-out">
            <slot name="BackBtn"></slot>
        </div>
        <div class=" flex justify-center ">
            <img src="../assets/elements/PinkWink.png" class="relative top-10 w-1/12  animate-bounce" />
            <slot name="BotChoose"></slot>
            <img src="../assets/elements/YellowWink.png" class="relative top-12 w-1/12 animate-bounce " />
        </div>
        <div class=" justify-center flex mt-6 ">
            <table class="w-4/6 ">
                <th class="w-1/3 ">
                    <slot name="Player"></slot>
                </th>
                <th class="w-1/3 ">
                    <slot name="GameResult"></slot>
                </th>
                <th class="w-1/3 ">
                    <slot name="Bot"></slot>
                </th>
            </table>
        </div>


        <div class="grid grid-cols-3 justify-between scale-100 mt-6">
            <slot name="Choice2"></slot>
        </div>
    </div> -->
</template>

<style scoped></style>