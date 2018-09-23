//设置全局表单提交格式
// Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            goods: [{
                id: '',
                title: '',
                price: '',
                image: '',
                category: '',
                brand: '',
                seller: '',

            }],
            searchMap: {
                keywords: '',
                category: '',
                brand: '',
                price: '',

                //分页选项，因为后端使用的Map接收，所以必须传过去一个对象
                pageCode: '',
                pageSize: '',

                //价格：升序、降序
                sort: '',
                field: 'price'
            },

            //模拟分类数据，这些数据应该是从数据库`分类表`中取出来的，这里只做一下模拟
            classifyData: {
                category: [],
                brand: [],
            },

            //checkbox选择的选项
            change: {
                category: [],
                brand: [],
                price: []
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 18, //每页显示的记录数
                totalPage: 20, //总记录数
                pageOption: [18, 25, 30], //分页选项
            },

            activeIndex: '1', //默认激活
        }
    },
    methods: {
        //搜索
        search() {
            this.$http.post('goods/search.do', this.searchMap).then(result => {
                this.goods = result.body.rows;

                //处理分页数据
                this.pageConf.pageSize = 18;
                this.pageConf.totalPage = result.body.total;

                console.log(result);
            });
        },

        //模拟得到数据的方法
        getClassifyData() {
            this.classifyData.category = ['手机', '平板电视', '平板电脑'];
            this.classifyData.brand = ['苹果', '华为', '小米', '魅族', '诺基亚', '联想', '索尼', '朵唯'];
        },

        //点击复选框触发的事件
        selectMethod(val) {
            this.searchMap.category = this.change.category[0];
            this.searchMap.brand = this.change.brand[0];
            this.searchMap.price = this.change.price[0];
            // this.searchMap.sort = this.change.sort[0];

            this.search(); //每次点击后都进行查询
            console.log(val);
        },

        //pageSize改变时触发的函数
        handleSizeChange(val) {
            console.log(val);
            this.searchMap.pageSize = val;
            this.searchMap.pageCode = this.pageConf.pageCode;
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            console.log(val);
            this.searchMap.pageCode = val;
            this.searchMap.pageSize = this.pageConf.pageSize;
            this.search(val, this.searchMap.pageSize);
        },
    },
    //声明周期钩子函数-->在data和methods渲染结束后执行
    created() {
        //首先加载分类数据
        this.getClassifyData();

        this.searchMap.pageCode = this.pageConf.pageCode;
        this.searchMap.pageSize = this.pageConf.pageSize;

        this.search();
    }
});