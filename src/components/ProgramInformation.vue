<template>
    <div class="info">
        <div style="display: flex; justify-content: space-evenly;">
            <div><h2>Input rate: {{ inputRate }}</h2></div>
            <div><h2>Product rate: {{ productRate }}</h2></div>
        </div>
        <div><h2>Products: {{ products }}</h2></div>
    </div>
</template>

<script>
export default{
    name: 'ProgramInformation',
    data(){
        return{
            inputRate: 0,
            productRate: 0,
            products: ""
        }
    },
    mounted:{
        
    },
    methods:{
        
    }
}
</script>

<style>
.info{
    background-color: rgba(91, 93, 96, 0.377);
    border-radius: 20px;
    margin: 10px;
    padding: 2px;
}
</style>