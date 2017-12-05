format 76

classinstance 128258 class_ref 143109 // VerMenu
 name ""  xyz 376 264 2000
classinstance 128386 class_ref 145413 // RestauranteSwing
 name ""  xyz 125 265 2000
note 129154 "Diagrama de  comunicacion de ver menu"
  xyzwh 389 341 2000 229 37
classinstance 129282 class_ref 142085 // Menu
 name ""  xyz 543 264 2000
classinstance 129666 class_ref 142597 // TipoPlatos
 name ""  xyz 375 136 2000
linkcanvas 128642
  from ref 128386 z 2001 to ref 128258
dirscanvas 128898 z 1000 linkcanvas_ref 128642
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 VerMenu()" xyz 272 245 3000
linkcanvas 130050
  from ref 128258 z 2001 to ref 129666
dirscanvas 130178 z 1000 linkcanvas_ref 130050
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 values()" xyz 336 205 3000
linkcanvas 130434
  from ref 128258 z 2001 to ref 129282
dirscanvas 130562 z 1000 linkcanvas_ref 130434
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "3 getPlatos()" xyz 458 245 3000
msgs
  msg operation_ref 157061 // "VerMenu(inout controller : GUIController)"
    forward ranks 1 "1" dirscanvas_ref 128898
    msgs
      msg operation_ref 142210 // "values() : TipoPlatos [1..*]"
	forward ranks 2 "1.1" dirscanvas_ref 130178
	no_msg
      msg operation_ref 165125 // "getPlatos(inout tipoPlato : TipoPlatos) : Consumicion"
	forward ranks 3 "1.2" dirscanvas_ref 130562
	no_msg
    msgsend
msgsend
end
