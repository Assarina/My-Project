import { defineStore, acceptHMRUpdate } from "pinia";
import { ref } from "vue";

export const usePlayer = defineStore("player", () => {
  const username = ref("");
  const score = ref(0);
  const id = ref(0);

  function setUsername(text) {
    username.value = text
  }

  return { username, score, id, setUsername };
});

if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(usePlayer, import.meta.hot));
}
