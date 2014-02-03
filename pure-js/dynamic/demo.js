jsPlumb.ready(function() {

    var fillColor = "gray";
    jsPlumb.Defaults.Connector = [ "Bezier", { curviness:25 } ];
    jsPlumb.Defaults.DragOptions = { cursor: "pointer", zIndex:2000 };
    jsPlumb.Defaults.PaintStyle = { strokeStyle:"gray", lineWidth:2 };
    jsPlumb.Defaults.EndpointStyle = { radius:3, fillStyle:"gray" };
    jsPlumb.Defaults.Anchors =  [ "AutoDefault", "AutoDefault" ];
    jsPlumb.Defaults.ConnectionsDetachable = false;
    jsPlumb.Defaults.Container = "dynamic-demo";


    // declare some common values:
    var arrowCommon = { foldback:0.3, fillStyle:fillColor, width:8 };
    // use three-arg spec to create two different arrows with the common values:
    var overlays = [
        [ "Arrow", { location:1 }, arrowCommon,
         ]
    ];

    jsPlumb.draggable(jsPlumb.getSelector(".window"));
    jsPlumb.draggable(jsPlumb.getSelector(".subwindow"),{containment:"#dynamicWindow1"});
    jsPlumb.connect({source:"dynamicWindow1", target:"dynamicWindow4", overlays:overlays});
    jsPlumb.connect({source:"dynamicWindow1_1", target:"dynamicWindow3", overlays:overlays});
    jsPlumb.connect({source:"dynamicWindow2", target:"dynamicWindow1_1", overlays:overlays});

});
