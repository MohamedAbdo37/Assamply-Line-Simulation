<template>
    <div class="sideBar">
        <div class="options">
            <Label>M color  </Label>
            <input type="color">
        </div>
        <div class="options">
            <Label>Q color  </Label>
            <input type="color">
        </div>
        <div class="options">
            <h3>Stop After:</h3>
            <div>
                <label for="time">Time  </label>
                <input type="radio" name="endCri" value="time" v-model="this.stopCri">
                <input type="number" name="endCri" placeholder="enter time of whole operation" v-show="this.stopCri=='time'">
            </div>
            <div>
                <label for="number">Number of items  </label>
                <input type="radio" name="endCri" value="number" v-model="this.stopCri">
                <input type="number" name="endCri" placeholder="enter number of items" v-show="this.stopCri=='number'" >
            </div>
            <div>
                <label for="none">None  </label>
                <input type="radio" name="endCri" value="none" v-model="this.stopCri" checked>
            </div>
            
        </div>
        <div class="options">
            <h3>Input:</h3>
            <div>
                <label for="random">Random  </label>
                <input type="radio" name="inputCri" value="random" v-model="this.inputCri" checked>
            </div>
            <div>
                <label for="number">Fixed  </label>
                <input type="radio" name="inputCri" value="freq" v-model="this.inputCri">
                <input type="number" name="inputCri" placeholder="items/sec" v-show="this.inputCri=='freq'">
            </div>
            
            
        </div>
        
        
        
    </div>
</template>

<script>
export default{
    name: 'SideBar',
    data(){
        return{
            stopCri: "none",
            inputCri: "random"
        }
    },
    methods:{
        
    }
}
</script>

<style scoped>
.sideBar{
    color: black;
    background-color: aliceblue;
    /* height: 200px; */
    width: 210px;
    position: absolute;
    /* transform: translate(1000%, 0%); */
    border-radius: 20px;
    padding: 10px;
    text-align: start;
    left: 1300px;
    margin-right: 10px;
    animation-name: slide;
    animation-duration: 1s;
}
@keyframes slide {
    from{
        left: 1500px;
    }
    to{
        left: 1300px;
    }
}
.options{
    margin-top: 10px;
}

input[type="number"]{
    width: 200px;
}
</style>