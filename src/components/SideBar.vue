<template>
    <div class="sideBar">
        <div class="options">
            <label for="machine">Machine color:</label>
            <input type="color" name="machine" id="machine" value="#089DDD">
        </div>
        <div class="options">
            <Label for="queue">Queue color </Label>
            <input type="color" name="queue" value="#ddaf08">
        </div>
        <div class="options">
            <h3>Stop After:</h3>
            <div>
                <label for="time">Time </label>
                <input type="radio" name="endCri" value="time" v-model="this.stopCri">
                <input type="number" name="endCri" placeholder="enter time of whole operation"
                    v-show="this.stopCri == 'time'">
            </div>
            <div>
                <label for="number">Number of items </label>
                <input type="radio" name="endCri" value="number" v-model="this.stopCri">
                <input type="number" name="endCri" placeholder="enter number of items" v-show="this.stopCri == 'number'">
            </div>
            <div>
                <label for="none">None </label>
                <input type="radio" name="endCri" value="none" v-model="this.stopCri" checked>
            </div>

        </div>
        <div class="options">
            <h3>Input Rate:</h3>
            <div>
                <label for="random">Random </label>
                <input type="radio" name="inputCri" value="random" v-model="this.inputCri" checked>
            </div>
            <div>
                <label for="number">Fixed </label>
                <input type="radio" name="inputCri" value="freq" v-model="this.inputCri">
                <input type="number" name="inputCri" placeholder="items/sec" v-show="this.inputCri == 'freq'">
            </div>


        </div>



    </div>
</template>

<script>
export default {
    name: 'SideBar',
    data() {
        return {
            stopCri: "none",
            inputCri: "random",
            queueColor:"#ddaf08",
            machineColor: "#089DDD",
        }
    },
    methods: {
    }
}
</script>

<style scoped>
.sideBar {
    color: black;
    background-color: aliceblue;
    height: 100%;
    width: 250px;
    position: absolute;
    /* transform: translate(1000%, 0%); */
    border-radius: 10px;
    padding: 10px;
    text-align: start;
    left: 85%;
    margin-top: 10px;
    animation-name: slide;
    animation-duration: 1s;
    z-index: 0;
}

@keyframes slide {
    from {
        left: 100%;
    }

    to {
        left: 85%;
    }
}

.options {
    margin-top: 10px;
}

input[type="number"]{
    width: 200px;
}

</style>