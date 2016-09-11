uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
attribute vec4 av4_colors;
varying vec4 vv4_Colors;
attribute vec4 av4_vertices;
varying float vf_alpha;
void main() {
   gl_Position = um4_projection * um4_view * um4_model * av4_vertices;
   vv4_Colors = av4_colors;
}
