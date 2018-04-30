uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
uniform float uf_alpha;

attribute vec4 av4_colors;
attribute vec2 av2_vertices;
attribute vec3 av3_uv;

varying vec2 vv2_texCoord;
varying float alpha;
varying vec4 vv4_Colors;



void main() {
   //gl_Position = um4_projection * um4_view * um4_model * vec4(av2_vertices.x, av2_vertices.y, 1.0, 1.0);

   gl_Position = um4_projection * um4_view * um4_model * vec4(av2_vertices.x, av2_vertices.y, 0.0, 1.0);
   vv2_texCoord = vec2(av3_uv.x, av3_uv.y);
   vv4_Colors = av4_colors;
   alpha = av3_uv.z;
}
