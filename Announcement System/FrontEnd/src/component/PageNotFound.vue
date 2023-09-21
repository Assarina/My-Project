<script>
</script>
<template>
    <div class="page_404">

        <div class="container">
            
            <img src="../assets/404gif.gif" alt="404 error" class="gif">
            <h1 class="text-center">404</h1>

            <h3>"PAGE NOT FOUND"</h3>
            <p>The page you were looking for does not exist!</p>

            <router-link :to="{ name: 'HomePage' }">
                <button class="HomePage">HOMEPAGE</button>
            </router-link>

        </div>
    </div>
</template>

<style scoped>
h1 {
    font-size: 200px;
    font-weight: bold;
    margin-top: -580px;
}
h3{
    margin-top: 200px;
    font-size: 50px;
}
.container{
    text-align: center;
    justify-content: center;
}
.page_404 {
    display: flex;
    justify-content: center;
    align-items: center;
}
.gif{
    display: flex;
    margin: 0 auto;
    margin-top: 120px;
    width: 600px;
    height: 450px;
}
.HomePage {
    margin: 0 auto;
    margin-top: 20px;
    width: fit-content;
    background-color: #c7c7c7;
    border-radius: 0.5rem;
    border: 10px solid #c7c7c7;
    transition: background-color 0.3s ease-out, border-color 0.3s ease-out;
}

.HomePage:hover {
    background-color: rgb(180, 180, 180);
    border-color: rgb(180, 180, 180);
    color: #ffffff;
}
</style>