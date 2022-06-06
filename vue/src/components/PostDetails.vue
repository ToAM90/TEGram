<template>
  <div>
    <div class="post-detail-bar">
      <p>here's a caption</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "post-interaction",
  props: {
    post: {
      postId: 0,
      accountId: 0,
      img: "",
      caption: "",
      postDate: "",
      privated: false,
      comments: [],
      liked: false,
      favorited: false,
      likesCount: 0,
    },
    data() {
      return {
        moreDetails: false,
      };
    },
  },
  methods: {
    toggleMoreDetails() {
      this.moreDetails = !this.moreDetails;
    },
  },
};
</script>

<style>
.caption {
  color: black;
}
.post-detail-bar {
  background: var(--panel-background-color);
  /* box-shadow: 0px 0px 5px rgb(172, 169, 169); */
  margin-bottom: 5px;
  border-radius: 0px 0px 4px 4px;
}
</style>