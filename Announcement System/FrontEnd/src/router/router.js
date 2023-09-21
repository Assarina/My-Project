import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "../component/HomePage.vue";
import Informations from "../component/Informations.vue";
import AddNewAnnounce from '../component/AddNewAnnounce.vue';
import PageNotFound from '../component/PageNotFound.vue';
import UserHomePage from '../component/UserHomePage.vue';
import UserInformations from '../component/UserInformations.vue';
import UpdateAnnounce from '../component/UpdateAnnounce.vue';
const history = createWebHistory(import.meta.env.BASE_URL)
const routes =[

    {
        path : '/admin/announcement',
        name : 'Announcements',
        component :HomePage
    },
    {
	path : '/admin/announcement/:id',
        name : 'Information',
        component :Informations
    },
    {
        path : '/admin/announcement/add',
        name : 'AddNewAnnounce',
        component :AddNewAnnounce
    },  
    {
        path : '/admin/announcement/:pathMatch(.*)*',
        name : 'PageNotFound',
        component :PageNotFound
    },
    {
        path : '/announcement',
        name : 'userAnnounce',
        component :UserHomePage
    },   
    {
        path : '/announcement/:id',
        name : 'UserInformations',
        component :UserInformations
    },
    {
        path : '/admin/announcement/:id/edit',
        name : 'UpdateAnnounce',
        component :UpdateAnnounce,
        props: true
    },
    {
        path: "/",
        redirect: "/admin/announcement",
    },
];

const router = createRouter({routes,history})

export default router;
