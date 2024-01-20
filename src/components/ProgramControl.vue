<template>
    <div class="controlBar">
        <button title="start"><img src="../assets/play.png"></button>
        <button title="pause the operation"><img src="../assets/pause.png"></button>
        <button title="stop sending inputs"><img src="../assets/stop.png"></button>
        <button title="repeat the operation"><img src="../assets/reload.png"></button>
        <button title="clear system"><img src="../assets/bin.png"></button>
        <button title="add a machine">+M</button>
        <button title="add a queue">+Q</button>
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
button:active{
    background-color: rgb(47, 152, 245);
}
img{
    width: 20px;
    height: 20px;
}
</style>