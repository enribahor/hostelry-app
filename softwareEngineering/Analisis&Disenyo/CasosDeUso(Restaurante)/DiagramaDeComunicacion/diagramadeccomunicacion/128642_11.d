format 76

classinstance 128002 class_ref 128258 // PestanaRestaurante
 name ""  xyz 196 181 2000
classinstance 128130 class_ref 128514 // Menu
 name ""  xyz 371 182 2000
classinstance 128258 class_ref 129410 // VerMenu
 name ""  xyz 361 80 2000
linkcanvas 128386
  from ref 128002 z 2001 to ref 128130
dirscanvas 128642 z 1000 linkcanvas_ref 128386
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  forward_label "1 verMenu" xyz 310 168 3000
linkcanvas 128514
  from ref 128258 z 2001 to ref 128130
dirscanvas 128898 z 1000 linkcanvas_ref 128514
  show_full_operations_definition default show_hierarchical_rank default drawing_language default show_msg_context default
  backward_label "2 mostrar" xyz 323 142 3000
msgs
  explicitmsg "verMenu"
    forward ranks 1 "1" dirscanvas_ref 128642
    msgs
      explicitmsg "mostrar"
	backward ranks 2 "1.1" dirscanvas_ref 128898
	no_msg
    msgsend
msgsend
end
