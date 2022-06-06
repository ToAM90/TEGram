<template>
  <div>
    
    <profile-header id="profile-header"></profile-header>
    <div class="page-title">Favorite Posts</div>
    <image-column class="profile-image-column" />
      
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from '../components/ImageColumn.vue';
import FavoriteService from '../services/FavoriteService';

export default {
    components: {ImageColumn, ProfileHeader },
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