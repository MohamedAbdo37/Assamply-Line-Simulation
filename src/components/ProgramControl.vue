<template>
    <div class="controlBar">
        <button>></button>
        <button>||</button>
        <button>Stop Input</button>
        <button>Replay</button>
        <button>Clear</button>
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
</style>