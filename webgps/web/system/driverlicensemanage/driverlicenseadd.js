var searchValue = '';
var lastMonth = new Date();
lastMonth.setMonth(lastMonth.getMonth()-1);
var startTime = (lastMonth).pattern("yyyy-M-d")+" 00:00:00";
var endTime = (new Date()).pattern("yyyy-M-d")+" 23:59:59";

var delbut = new Ext.Action({
	text: '增加',
	id : 'delbut',
	handler: function(){
		Ext.MessageBox.confirm('提示', '您确定要添加新数据吗?', function (btn){
			if(btn=='yes'){
				/*var tmpdeviceId = Ext.getCmp('frmdeviceId').getValue();
				if(tmpdeviceId.length<=0){
					Ext.Msg.alert('提示', '请选择终端!');
					return;
				}*/
				var tmpemployeeNum = Ext.getCmp('frmemployeeNum').getValue();
				if(tmpemployeeNum.length<=0){
					Ext.Msg.alert('提示', '请输入员工编号!');
					return;
				}
				var tmpemployeeWorknum = Ext.getCmp('frmemployeeWorknum').getValue();
				if(tmpemployeeWorknum.length<=0){
					Ext.Msg.alert('提示', '请输入员工工号!');
					return;
				}
				var tmpemployeeName = Ext.getCmp('frmemployeeName').getValue();
				if(tmpemployeeName.length<=0){
					Ext.Msg.alert('提示', '请输入员工姓名!');
					return;
				}
				var tmpemployeeId = Ext.getCmp('frmemployeeId').getValue();
				if(tmpemployeeId.length<=0){
					Ext.Msg.alert('提示', '请输入员工身份证号!');
					return;
				}
				var tmpdepartment = Ext.getCmp('frmdepartment').getValue();
				if(tmpdepartment.length<=0){
					Ext.Msg.alert('提示', '请输入所在部门!');
					return;
				}
				var tmpexamineDate = Ext.getCmp('frmexamineDate').getValue();
				if(tmpexamineDate.length<=0){
					Ext.Msg.alert('提示', '请选择年审日期!');
					return;
				}
				var tmpexpireDate = Ext.getCmp('frmexpireDate').getValue();
				if(tmpexpireDate.length<=0){
					Ext.Msg.alert('提示', '请选择到期日期!');
					return;
				}
				var tmpcondition = Ext.getCmp('frmcondition').getValue();
				if(tmpcondition.length<=0){
					Ext.Msg.alert('提示', '请输入年审情况!');
					return;
				}
				var tmpdemo = Ext.getCmp('frmdemo').getValue();
				if(tmpdemo.length<=0){
					Ext.Msg.alert('提示', '请输入备注!');
					return;
				}
				Ext.Msg.show({
					msg: '正在保存 请稍等...',
					progressText: '保存...',
					width:300,
					wait:true,
					icon:'ext-mb-download'
				});
				Ext.Ajax.request({
					url:path+'/driverLicense/driverLicense.do?method=addDriverLicense',
					method :'POST', 
					params:{
						employeeNum: encodeURI(tmpemployeeNum), employeeWorknum: encodeURI(tmpemployeeWorknum),
						employeeName: encodeURI(tmpemployeeName), employeeId: encodeURI(tmpemployeeId), department: encodeURI(tmpdepartment),
						examineDate: tmpexamineDate, expireDate: tmpexpireDate, condition: encodeURI(tmpcondition), demo: encodeURI(tmpdemo)
					 },
					 //timeout : 10000,
					 success : function(request) {
					   var res = Ext.decode(request.responseText);
					 	if(res.result==1){
					 		store.load({params:{start: 0, limit: 20, searchValue: encodeURI(searchValue) ,startTime: startTime, endTime: endTime}});
					   		Ext.Msg.alert('提示', '操作成功');
					   		return;
					   }else{
					   		//store.reload();
					   		Ext.Msg.alert('提示', "操作失败!");
					   		return;
					   }
					 },
					 failure : function(request) {
						 Ext.Msg.alert('提示', "操作失败!");
					 }
					});
			}
		});
	},
	iconCls: 'icon-save'
});

	var formPanel = new Ext.FormPanel({
		labelWidth: 100,
		frame: true,
		flex: 1,
		bodyStyle: 'padding:5px 5px 0',
		autoScroll : true,
		width: 200,
		defaults: {width: 150},
		defaultType: 'textfield',
		items: [{
			fieldLabel: '员工编号',
		    id: 'frmemployeeNum',
		    width: 150,
		    allowBlank:false
		},{
			fieldLabel: '员工工号',
		    id: 'frmemployeeWorknum',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '员工姓名',
		    id: 'frmemployeeName',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '员工身份证号',
		    id: 'frmemployeeId',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '所在部门',
		    id: 'frmdepartment',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '年审日期',
		    xtype: 'datefield',
		    id: 'frmexamineDate',
		    editable: false,
		    format: 'Y-m-d',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '到期日期',
		    xtype: 'datefield',
		    id: 'frmexpireDate',
		    editable: false,
		    format: 'Y-m-d',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '年审情况',
		    id: 'frmcondition',
		    width: 150,
		    allowBlank:false
		},{
		    fieldLabel: '备注',
		    id: 'frmdemo',
		    width: 150,
		    allowBlank:false
		},{
		    xtype: 'hidden',
		    id: 'frmdeviceId'
		}]
	});
	
	/*var loader = new Ext.tree.TreeLoader({
		dataUrl: path+'/manage/termGroupManage.do?actionMethod=groupListForEntTerminalNoCheck'	
	});
	
	var root = new Ext.tree.AsyncTreeNode({
			text : '',
			id : '-100',
			draggable : false // 根节点不容许拖动
	});
	
    var api = new Ext.tree.TreePanel({
		flex: 1,
        rootVisible:false,
        lines:true,
        autoScroll:true,
        width:170,
        loader: loader,
        root:root
	});*/
	
	var simple = new Ext.Panel({
        region: 'center',
		layout:'vbox',
		layoutConfig: {
		    align : 'stretch',
		    pack  : 'start'
		},
		items: [
		    formPanel//,api
		]
	});

var proxy = new Ext.data.HttpProxy({
	url: path+'/driverLicense/driverLicense.do?method=listDriverLicense'
});

var reader = new Ext.data.JsonReader({
    totalProperty: 'total',
    successProperty: 'success',
    idProperty: 'id',
    root: 'data'
}, [
    {name: 'id'},
    {name: 'employeeNum'},
    {name: 'employeeWorknum'},
    {name: 'employeeName'},
    {name: 'employeeId'},
    {name: 'department'},
    {name: 'examineDate'},
    {name: 'expireDate'},
    {name: 'condition'},
    {name: 'demo'}
]);

var store = new Ext.data.Store({
	autoLoad: {params:{start: 0, limit: 20, searchValue: encodeURI(searchValue) ,startTime: startTime, endTime: endTime}},
    id: 'store',
    restful: true,     // <-- This Store is RESTful
    proxy: proxy,
    reader: reader,
    listeners:{
    	beforeload:{
    		fn: function(thiz,options){
    			this.baseParams ={
    				searchValue: encodeURI(searchValue) ,startTime: startTime, endTime: endTime
    			};
    		}
    	}
    }
});

var userColumns =  [
	new Ext.grid.RowNumberer({header:'序号',width:40}),
    //{id:'deviceId',header: "deviceId", width: 40, sortable: true, dataIndex: 'deviceId',hidden:true},
    {header: "员工编号",fixed:true, width: 100, sortable: true, dataIndex: 'employeeNum'},
    {header: "员工工号",fixed:true, width: 100, sortable: true, dataIndex: 'employeeWorknum'},
    {header: "员工姓名",fixed:true, width: 100, sortable: true, dataIndex: 'employeeName'},
    {header: "员工身份证号",fixed:true, width: 100, sortable: true, dataIndex: 'employeeId'},
    {header: "所在部门",fixed:true, width: 100, sortable: true, dataIndex: 'department'},
    {header: "年审日期",fixed:true, width: 100, sortable: true, dataIndex: 'examineDate'},
    {header: "到期日期",fixed:true, width: 100, sortable: true, dataIndex: 'expireDate'},
    {header: "年审情况",fixed:true, width: 100, sortable: true, dataIndex: 'condition'},
    {header: "备注",fixed:true, width: 100, sortable: true, dataIndex: 'demo'}
];

var userGrid = new Ext.grid.GridPanel({
	region: 'west',
    width: 500,
    loadMask: {msg:'查询中...'},
    enableColumnHide : false,
    store: store,
    columns : userColumns,
    margins: '0 0 0 0',
	tbar: [new Ext.form.DateField({
		id: 'startDateField',
		fieldLabel: 'Date',
		format:'Y-m-d',
		width: 100,
		editable: false,
		value: lastMonth
	}),'-',new Ext.form.DateField({
		width: 100,
		fieldLabel: 'Date',
		format:'Y-m-d',
		editable: false,
		value: new Date(),
	    id: 'endDateField'
	}),'-',new Ext.form.TextField({
	    id: 'DeviceIdField',
	    width: 80,
	    enableKeyEvents: true,
	    listeners: {
        	keypress : function( textField, e ) {
		        if (e.getKey() == e.ENTER) {
		        	searchValue = Ext.getCmp('DeviceIdField').getValue();
				    startTime = Ext.getCmp('startDateField').getValue().format('Y-m-d')+" 00:00:00";
				    endTime = Ext.getCmp('endDateField').getValue().format('Y-m-d')+" 23:59:59";
			    	store.load({params:{start:0, limit:20, searchValue: encodeURI(searchValue) ,startTime: startTime, endTime: endTime}});
			    }
        	}
        }
	}),'-',new Ext.Action({
		text: '查询',
		handler: function(){
			searchValue = Ext.getCmp('DeviceIdField').getValue();
			startTime = Ext.getCmp('startDateField').getValue().format('Y-m-d')+" 00:00:00";
			endTime = Ext.getCmp('endDateField').getValue().format('Y-m-d')+" 23:59:59";
	    	store.load({params:{start:0, limit:20, searchValue: encodeURI(searchValue) ,startTime: startTime, endTime: endTime}});
	    },
		iconCls: 'icon-search'
	}),'-',delbut
    ],
    bbar: new Ext.PagingToolbar({
        pageSize: 20,
        store: store,
        displayInfo: true,
        displayMsg: '第{0}到第{1}条数据 共{2}条',
        emptyMsg: "没有数据"
    })
});

Ext.onReady(function(){
    var viewport = new Ext.Viewport({layout: 'border',items: [simple,userGrid]});
    /*api.on('click',function(node, e) {
		var id_arr='';
		//"18603261126@#1@#18603261126@#1@#00:00@#23:59@#car@#127"
		id_arr = node.id.split('@#');
		if(id_arr.length >= 8){
			var deviceIdTmp = id_arr[0];
			Ext.getCmp('frmdevice').setValue(node.text);
			Ext.getCmp('frmdeviceId').setValue(deviceIdTmp);
			
		}
	});*/
});