<template>
    <div class="controlBar">
        <button title="start" @click="play"><img src="../assets/play.png" alt="start operation"></button>
        <button title="pause the operation" @click="pause"><img src="../assets/pause.png" alt="pause operation"></button>
        <button title="stop sending inputs" @click="stop"><img src="../assets/stop.png" alt="stop input"></button>
        <button title="repeat the operation" @click="replay"><img src="../assets/reload.png"
                alt="repeat operation"></button>
        <button title="clear system" @click="clear"><img src="../assets/bin.png" alt="clear"></button>
        <button title="add a machine" @click="addM"><img src="../assets/add.png" alt="add machine">Machine</button>
        <button title="add a queue" @click="addQ"><img src="../assets/add.png" alt="add queue">Queue</button>
        <button title="create a Line" @click="addR"><img src="../assets/add.png" alt="create Line">Line</button>
    </div>
</template>

<script>
// import axios from 'axios'
export default {
    name: 'ProgramControl',
    props: ['m', 'q'],
    data() {
        return {
            machine: this.m,
            queue: this.q,
            start: false,
            playID: undefined,
            switchPlay: true
        }
    },
    methods: {
        async pause() {
            // await fetch(`http://localhost:8081/play?initialQueue=${group.name}`, {
            //     method: "GET",
            // })
            clearInterval();
            this.start = false;
        },
        async stop() {
            // await fetch(`http://localhost:8081/play?initialQueue=${group.name}`, {
            //     method: "GET",
            // })
        },
        async play() {
            await fetch(`http://localhost:8081/play?initialQueue=Q0`, {
                method: "GET",
            }).then(() => this.start = true)
            this.switchPlay = false
        },
        async replay() {
            // await fetch(`http://localhost:8081/replay`, {
            //     method: "GET",
            // })
            this.$emit('snap', true)
            this.switchPlay = false
        },
        addM() {
            if (this.switchPlay) {
                ++this.machine
                this.$emit('mChange', this.machine);
                this.$emit('clear', false);
                // this.$emit('addR', FontFaceSetLoadEvent);
            }
        },
        addQ() {
            if (this.switchPlay) {
                ++this.queue
                this.$emit('qChange', this.queue);
                this.$emit('clear', false);
                this.$emit('addR', false);
            }
        },
        addR() {
            if (this.switchPlay) {
                this.$emit('addR', true);
                this.$emit('clear', false);
            }
        }, clear() {
            this.machine = -1
            this.queue = 0
            this.$emit('mChange', this.machine);
            this.$emit('qChange', this.queue);
            this.$emit('addR', false);
            this.$emit('clear', true);
            this.switchPlay = true;
        },
        async fetchMachinesStatus() {
            let list = await fetch("http://localhost:8081/getMachineStatus", {
                method: "GET"
            });
            console.log(list);
            return list;
        },
        async fetchQueuesStatus() {
            let list = await fetch("http://localhost:8081/getQueueStatus", {
                method: "GET"
            })
            console.log(list);
            return list;
        }
    },
    created() {

    },
    watch: {
        start() {
            console.log(this.start);
            if (this.start === true) {
                console.log(this.start);
                this.playID = setInterval(async () => {
                    this.$emit("play", true);
                }, 15000);
            }
            else {
                console.log(this.start);
                clearInterval(this.playID);
            }
        }
    },
}
</script>

<style scoped>
.controlBar {
    display: flex;
    justify-content: space-evenly;
    background-color: rgb(140, 198, 249);
    padding: 10px;
    border-radius: 20px;
    margin: 10px;
}

button {
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

button:hover {
    background-color: rgb(92, 173, 244);
}

button:active {
    background-color: rgb(47, 152, 245);
}

img {
    width: 20px;
    height: 20px;
}
</style>