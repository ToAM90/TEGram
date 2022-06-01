<template>
  <div class="my-images" > 
    <!-- <div class='indiv-image' v-for="image in images" v-bind:key="image.id"> -->
      <img class="image" v-for="image in images" v-bind:key="image.id" v-bind:src="image" alt="" />
      <!-- <div class='image-info'>
        <p>username</p>
        <p>caption<br>caption<br>caption<br>caption<br></p>
      </div> -->
    </div>
  <!-- </div> -->
</template>

<script>
export default {
  name: 'image-column',
  computed: {
    images() {
      return this.$store.state.images;
    },
  }
};
</script>

<style>
  .my-images {
    margin-left: 275px;
    line-height: 0;
    
    -webkit-column-count: 3;
    -webkit-column-gap:   10px;
    -moz-column-count:    3;
    -moz-column-gap:      10px;
    column-count:         3;
    column-gap:           10px;  
  }

  .my-images .image {
    /* margin: 20px; */
    margin-left: 20px;
    margin-right: 20px;
    width: calc(100% - 40px)!important;
    height: auto !important;
    border: 4px solid blue;
    border-top: 0px
  }

  /* .my-images
  {
    display: flex;
    flex-direction: row;
    
    flex-wrap: wrap;
    align-items: center;
    background-color: grey;
  } */
 
  .image-info {
    border-left: solid black 3px;
    /* max-width: 20%; */
    color: black;
    padding-left: 5px
  }
  .image {
    /* margin: 20px; */
    /* max-width: calc(60%); */
  }
</style>