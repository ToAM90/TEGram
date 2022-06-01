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
    margin-left: 295px;
    line-height: 0;
    
    -webkit-column-count: 3;
    -webkit-column-gap:   0px;
    -moz-column-count:    3;
    -moz-column-gap:      0px;
    column-count:         3;
    column-gap:           0px;  
    background: var(--primary-background-color);
  }

  .my-images .image {
    /* margin: 20px; */
    margin-left: 0px;
    margin-right: 0px;
    width: calc(100% - 40px)!important;
    height: auto !important;
    /* border: 0px solid grey; */
    box-shadow: 0px 0px 5px rgb(172, 169, 169);
    border-radius: 5%;
    
    margin-top: 5px;
    margin-bottom: 5px;
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