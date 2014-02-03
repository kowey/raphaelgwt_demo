function gwtjsplumbdemo() {

    var fillColor = "gray";
    jsPlumb.Defaults.Container = $("body");
    jsPlumb.Defaults.Connector = [ "Bezier", { curviness:25 } ];
    jsPlumb.Defaults.DragOptions = { cursor: "pointer", zIndex:2000 };
    jsPlumb.Defaults.PaintStyle = { strokeStyle:"gray", lineWidth:2 };
    jsPlumb.Defaults.EndpointStyle = { radius:3, fillStyle:"gray" };
    jsPlumb.Defaults.Anchors =  [ "AutoDefault", "AutoDefault" ];

    // declare some common values:
    var arrowCommon = { foldback:0.3, fillStyle:fillColor, width:8 };
    // use three-arg spec to create two different arrows with the common values:
    var overlays = [
        [ "Arrow", { location:1 }, arrowCommon,
         ]
    ];

    //jsPlumb.connect({source:"kowey-box1", target:"concept1_curve", overlays:overlays});
    jsPlumb.draggable($(".koweybox"));
    //jsPlumb.draggable($("#kowey-box1"));
    jsPlumb.draggable($(".koweydrag"));
 }

function make_draggable(draggableId) {
    jsPlumb.draggable($(draggableId));
}