import { createRouter, createWebHistory } from 'vue-router'
import ScoreBoard from "../component/ScoreBoard.vue"
import AboutUs from "../views/AboutUs.vue"
import MainMenu from "../component/MainMenu.vue"



const history = createWebHistory()
const routes =[
    {
        path : '/ScoreBoard',
        name : 'ScoreBoard',
        component : ScoreBoard
    },
    {
        path : '/AboutUs',
        name : 'AboutUs',
        component : AboutUs
    },
    {
        path : '/',
        name : 'MainMenu',
        component : MainMenu
    }
]

const router = createRouter({routes,history})

export default router