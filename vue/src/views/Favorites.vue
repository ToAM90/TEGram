<template>
  <div>
    
    <side-bar id="side-bar-home"/>
    <h1 class="page-title">Favorite Posts</h1>
    <image-column class="image-column" />
      
  </div>
</template>

<script>
import SideBar from "../components/SideBar.vue";
import ImageColumn from '../components/ImageColumn.vue';
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
.page-title {
  width: calc(100% - 315px);
  margin-left: 295px;
  margin-right: auto;
  padding-top: 20px;
  color: beige;
}

</style>