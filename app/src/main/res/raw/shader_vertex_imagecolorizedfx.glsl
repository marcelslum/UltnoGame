uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
uniform float uf_alpha;
uniform float uf_time;
attribute vec4 av4_vertices;
attribute vec4 av4_colors;
attribute vec2 av2_uv;
varying vec4 vv4_Colors;
varying vec2 vv2_texCoord;
varying float vf_alpha;
void main() {
   gl_Position = um4_projection * um4_view * um4_model * av4_vertices;
   gl_Position.x += uf_time;
   gl_Position.y += uf_time;
   vv2_texCoord = av2_uv;
   vv4_Colors = av4_colors;
}
