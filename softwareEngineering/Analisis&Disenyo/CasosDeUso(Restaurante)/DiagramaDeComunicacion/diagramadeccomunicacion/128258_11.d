format 76

classinstance 128002 class_ref 128130 // Plato
 name ""  xyz 307 149 2000
classinstance 128386 class_ref 128258 // PestanaRestaurante
 name ""  xyz 67 310 2000
classinstance 128514 class_ref 128514 // Menu
 name ""  xyz 305 310 2000
classinstance 128642 class_ref 128642 // ListaPlatos
 name ""  xyz 293 434 2000
linkcanvas 129026
  from ref 128386 z 2001 to ref 128514
dirscanvas 129154 z 1000 linkcanvas_ref 129026
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 modificarMenu" xyz 198 297 3000
linkcanvas 129794
  from ref 128514 z 2001 to ref 128642
dirscanvas 129922 z 1000 linkcanvas_ref 129794
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "2 borrarPlato
3 modifica" xyz 341 377 3000
msgs
  explicitmsg "modificarMenu"
    forward ranks 1 "1" dirscanvas_ref 129154
    no_msg
  explicitmsg "borrarPlato"
    forward ranks 2 "2" dirscanvas_ref 129922
    no_msg
  explicitmsg "modifica"
    forward ranks 3 "3" dirscanvas_ref 129922
    no_msg
msgsend
end
