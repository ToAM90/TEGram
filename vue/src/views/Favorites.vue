<template>
  <div></div>
</template>

<script>
import FavoriteService from '../services/FavoriteService';
export default {
    name: "favorites",
    created(){
        FavoriteService.getFavoritePosts().then((response) => {
            this.$store.commit("INITIALIZE_POSTS", response.data)
        })
    }

}
</script>

<style>

</style>