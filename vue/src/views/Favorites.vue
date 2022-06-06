<template>
  <div>
      <image-column />
      <side-bar />
  </div>
</template>

<script>
import ImageColumn from '../components/ImageColumn.vue';
import SideBar from '../components/SideBar.vue';
import FavoriteService from '../services/FavoriteService';

export default {
    components: {ImageColumn, SideBar },
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