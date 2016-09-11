uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
uniform float uf_alpha;
attribute vec4 av4_vertices;
attribute vec2 av2_uv;
varying vec2 vv2_texCoord;
varying float vf_alpha;
void main() {
    gl_Position = um4_projection * um4_view * um4_model * av4_vertices;
    vv2_texCoord = av2_uv;
    vf_alpha = uf_alpha;
}
