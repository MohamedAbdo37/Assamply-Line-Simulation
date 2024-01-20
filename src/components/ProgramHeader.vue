<template>
    <div class="header">
        <div style="display: flex;">
            <img src="../assets/logo.png">
            <h3> Name</h3>
        </div>
        <div>
            <button>
                |||
            </button>
        </div>
        <SideBar v-if="sideBarToggle"></SideBar>
    </div>
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

    }
}
</script>

<style scoped>
.header{
    display: flex;
    width:100%;
    justify-content: space-between;
   
    margin: 0px;
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
    border-radius: 25px;
    background-color: rgba(240, 248, 255, 0.285);
    border: none;
    outline: none;
}
button:hover{
    background-color: rgb(92, 173, 244);

}
</style>