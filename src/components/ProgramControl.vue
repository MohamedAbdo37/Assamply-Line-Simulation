<template>
    <div class="controlBar">
        <button title="start"><img src="../assets/play.png" alt="start operation"></button>
        <button title="pause the operation"><img src="../assets/pause.png" alt="pause operation"></button>
        <button title="stop sending inputs"><img src="../assets/stop.png" alt="stop input"></button>
        <button title="repeat the operation"><img src="../assets/reload.png" alt="repeat operation"></button>
        <button title="clear system" @click="clear"><img src="../assets/bin.png" alt="clear"></button>
        <button title="add a machine" @click="addM"><img src="../assets/add.png" alt="add machine">Machine</button>
        <button title="add a queue" @click="addQ"><img src="../assets/add.png" alt="add queue">Queue</button>
    </div>
</template>

<script>
export default{
    name: 'ProgramControl',
    props: ['m', 'q'],
    data(){
        return{
            machine: this.m,
            queue: this.q
        }
    },
    methods:{
        addM(){
            ++this.machine
            this.$emit('mChange', this.machine);
        },
        addQ(){
            ++this.queue
            this.$emit('qChange', this.queue);
        },
        clear(){
            this.machine = -1
            this.queue = 0
            this.$emit('mChange', this.machine);
            this.$emit('qChange', this.queue);
        }
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