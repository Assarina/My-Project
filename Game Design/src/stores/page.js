import { defineStore,acceptHMRUpdate } from "pinia";
import {ref,computed} from 'vue'

export const usePage = defineStore('page', () => {
    const page = ref('mainmenu')
    const setPage = (text) => {
        page.value = text
    }
    return {page,setPage}
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(usePage, import.meta.hot))
}