/**
 * 实例：
 * js:    $(function() {
        image.init("myeditor", "upload");
        image.show("image");
    });
    html : 
    	<input id="upload" name='upload' type="text" value=""/>
        <script id="myeditor"></script>
        <input type="button" id='image' value='上传图片'/>
 * @param $
 */

(function($) {
    var image = {
        editor: null,
        init: function(editorid, keyid) {
            var _editor = this.getEditor(editorid);
            _editor.ready(function() {
                _editor.setDisabled();
                _editor.hide();
                _editor.addListener('beforeInsertImage', function(t, args) {
                    $("#" + keyid).val(args[0].src);
                });
            });
        },
        getEditor: function(editorid) {
            this.editor = UE.getEditor(editorid);
            return this.editor;
        },
        show: function(id) {
            var _editor = this.editor;
            //注意这里只需要获取编辑器，无需渲染，如果强行渲染，在IE下可能会不兼容（切记）
            //和网上一些朋友的代码不同之处就在这里
            $("#" + id).click(function() {
                var image = _editor.getDialog("insertimage");
                image.render();
                image.open();
            });
        },
        render: function(editorid) {
            var _editor = this.getEditor(editorid);
            _editor.render();
        }
    };
})(jQuery);