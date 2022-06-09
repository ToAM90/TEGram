<template>
  <main id="following-page">
    <side-bar id="side-bar-home" />
    <header class="follow-header">
      <h1 class="page-title-text">Following</h1>
      <img
        class="page-title-icon"
        src="@/assets/groups_2_FILL0_wght400_GRAD0_opsz48.png"
        alt=""
      />
    </header>

    <div id="follow-list">
      <div
        id="list-accounts"
        v-for="account in $store.state.followAccounts"
        v-bind:key="account.accountId"
      >
        <router-link
          v-bind:to="{ name: 'profile', params: { id: account.accountId } }"
        >
          <img class="account-image" :src="account.profileImg" />
        </router-link>
        <span v-on:click="toggleFollow(account.followed, account.accountId)">
        <img class="follow-icon" v-if="account.followed" src="@/assets/delete-friend.png" />
      <img class="follow-icon" v-else src="@/assets/add-friend.png" />
        <p
          class="account-name"
          v-on:click="$router.push(`/profile/${account.accountId}`)"
        >
          {{ account.displayName }}
          
        </p>

      
</span>
      </div>
    </div>
  </main>
</template>

<script>
import FollowService from "../services/FollowService";
import SideBar from "../components/SideBar.vue";

export default {
  name: "following",
  components: {
    SideBar,
  },
  methods: {
    toggleFollow(followed, accountId) {
      if (followed) {
        FollowService.unfollow(accountId);
        this.$store.commit("TOGGLE_FOLLOW_FOLLOW", accountId);
      } else {
        FollowService.follow(accountId);
        this.$store.commit("TOGGLE_FOLLOW_FOLLOW", accountId);
      }
    },
  },
  created() {
    FollowService.listFollowers().then((response) => {
      this.$store.commit("SET_FOLLOW_ACCOUNTS", response.data);
    });
  },
};
</script>

<style>

</style>