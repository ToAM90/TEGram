<template>
  <div class="post-header-bar">
    <div>{{ this.$store.state.account.displayName }}</div>
    <router-link
      class="display-name"
      v-bind:to="{ name: 'profile', params: { id: account.accountId } }"
    >
      <p class="display-name">{{ this.$store.state.account.displayName }}</p>
    </router-link>
  </div>
</template>

<script>
import AccountService from "../services/AccountService";
export default {
  name: "post-header",
  props: {
    accountId: {
      type: Number,
    },
  },
  data() {
    return {
      account: {
        accountId: 0,
        displayName: "",
        isFetching: true,
      },
    };
  },
  //     mounted(){
  //         console.log( "before id" + this.accountId)
  //         AccountService.getAccountOther(this.accountId).then((response) => {
  //         this.account = response.data;
  //     });
  // },
  methods: {
    getAccount() {
      AccountService.getAccountOther(this.accountId).then((response) => {
        this.account = response.data;
      });
    },
  },
};
</script>

<style>
.post-header-bar {
  /* background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654570644/TE_GRAM/u7k1krgvbdjhnfujrwhy.png"); */
  background: #d1d1d3;
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  border-radius: 4px 4px 0px 0px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.display-name {
  margin: auto;
  /* padding: 8px; */

  color: #ff48a3;

  font-size: 1.3rem;
  font-family: "Montserrat", sans-serif;
  font-weight: 600;
}
</style>