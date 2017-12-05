format 76

classinstance 129666 class_ref 145413 // RestauranteSwing
 name ""  xyz 98 121 2000
classinstance 129794 class_ref 143237 // GenerarFactura
 name ""  xyz 302 122 2000
classinstance 130178 class_ref 143365 // Mesa
 name ""  xyz 498 121 2000
note 130562 "Diagrama de comunicación generar factura"
  xyzwh 419.5 206.5 2000 229 37
linkcanvas 129922
  from ref 129666 z 2001 to ref 129794
dirscanvas 130050 z 1000 linkcanvas_ref 129922
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 GenerarFactura()" xyz 205 102 3000
linkcanvas 130306
  from ref 129794 z 2001 to ref 130178
dirscanvas 130434 z 1000 linkcanvas_ref 130306
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 generarFactura()" xyz 400 102 3000
msgs
  msg operation_ref 150021 // "GenerarFactura(inout rest : GUIController)"
    forward ranks 1 "1" dirscanvas_ref 130050
    msgs
      msg operation_ref 168197 // "generarFactura() : String"
	forward ranks 2 "1.1" dirscanvas_ref 130434
	no_msg
    msgsend
msgsend
end
