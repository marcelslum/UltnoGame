uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
uniform float uf_alpha;
attribute vec2 av2_vertices;
attribute vec4 av4_color;
varying vec4 vv4_color;

void main() {
   gl_Position = um4_projection * um4_view * um4_model * vec4(av2_vertices.x, av2_vertices.y, 0.0, 1.0);
   vv4_color = av4_color;
}
