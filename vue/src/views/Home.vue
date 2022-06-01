<template>
  <div class="home">
    <SideBar></SideBar>
    <div class="my-images" v-for="image in images" v-bind:key="image.id">
      <img v-bind:src="image" alt="" />
    </div>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";

export default {
  name: "home",
  computed: {
    images() {
      return this.$store.state.images;
    },
  },
  components: {
    SideBar,
  },
};
</script>

<style>
.home {
  display: flex;
  flex-direction: row;
  width: 100vw;
}
img {
  max-width: 30vw;
}
.my-images {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  max-width: 100%;
}
</style>