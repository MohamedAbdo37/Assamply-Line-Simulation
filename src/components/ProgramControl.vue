<template>
    <div class="controlBar">
        <button><img src="../assets/play.png"></button>
        <button><img src="../assets/pause.png"></button>
        <button><img src="../assets/stop.png"></button>
        <button><img src="../assets/reload.png"></button>
        <button><img src="../assets/bin.png"></button>
        <button>+M</button>
        <button>+Q</button>
    </div>
</template>

<script>
export default{
    name: 'ProgramControl',
    data(){
        return{

        }
    },
    methods:{
        
    }
}
</script>

<style scoped>
.controlBar{
    display: flex;
    justify-content: space-evenly;
    background-color: rgb(140, 198, 249);
    padding: 10px;
    border-radius: 20px; 
    margin: 10px;
}

button{
    width: 200px;
    /* height: 50px; */ 
    color: rgb(20, 20, 20);
    font-weight: bold;
    font-size: large;
    cursor: pointer;
    border-radius: 10px;
    background-color: rgba(240, 248, 255, 0.285);
    border: none;
    outline: none;

}
button:hover{
    background-color: rgb(92, 173, 244);

}

img{
    width: 20px;
    height: 20px;
}
</style>