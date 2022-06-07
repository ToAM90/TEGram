<template>
  <div class="post-header-bar">
      <router-link class="display-name" v-bind:to="{name: 'profile', params: {id: accountId}}">
          {{accountId}}
          {{account.accountId}}
      {{account.displayName}}
        {{username}}
      </router-link>
  </div>
</template>

<script>
import AccountService from "../services/AccountService";
export default {
    name: "post-header",
    props: {accountId: {
        type: Number,
        required: true,
        default: 0}
    },
    data(){
        return {
            account: {
                accountId: 0,
                displayName: '',
            }
        }
    },
    computed: {
        username(){
            return this.account.displayName.toUpperCase();
        }
    },
    mounted(){
            console.log( "before" + this.account)
            console.log( "before id" + this.accountId)
        AccountService.getAccountOther(this.accountId).then((response) => {
            this.account = response.data; 
            console.log( "response" + response.data)}
            );
            console.log("after" + this.account)
            
    }
}
</script>

<style>
.post-header-bar {
  background: var(--panel-background-color);
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  color: black;
  border-radius: 4px 4px 0px 0px;
}

.display-name {
  margin: auto;
}
</style>