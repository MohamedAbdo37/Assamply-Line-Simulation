<template>
    <div class="header">
        <div style="display: flex; justify-content: space-between;">
            <img src="../assets/logo.png">
            <h2> ProSim </h2>
        </div>
        <div>
            <button @click="toggleSideBar">
                |||
            </button>
        </div>
    </div>
    <SideBar v-show="this.sideBarToggle"></SideBar>
</template>

<script>
import SideBar from './SideBar.vue'

export default{
    name: 'ProgramHeader',
    components:{
        SideBar
    },
    data(){
        return{
            sideBarToggle: false
        }
    },
    methods:{
        toggleSideBar(){
            this.sideBarToggle = !this.sideBarToggle
        }
    }
}
</script>

<style scoped>
.header{
    display: flex;
    width:100%;
    justify-content: space-between;
    margin: 0px;
    z-index: -1;
}
img{
    width: 50px;
    height: 50px;
}
button{
    width: 50px;
    height: 50px;
    color: white;
    font-weight: bold;
    cursor: pointer;
    border-radius: 10px;
    background-color: rgba(240, 248, 255, 0.285);
    border: none;
    outline: none;
}
button:hover{
    background-color: rgb(92, 173, 244);

}
</style>