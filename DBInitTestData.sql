------------------------------------------- video type test data -------------------------------------------

INSERT INTO public.video_types(id, created_at, updated_at, archived, canonical_name, description, long_name)
VALUES (
    0,
    current_timestamp,
    current_timestamp,
    false,
    'basic',
    'normal video',
    'basic video type'
);

INSERT INTO public.video_types(id, created_at, updated_at, archived, canonical_name, description, long_name)
VALUES (
    1,
    current_timestamp,
    current_timestamp,
    false,
    'broadcast',
    'basic broadcast with live stream, like MeetUp',
    'broadcast video type'
);

INSERT INTO public.video_types(id, created_at, updated_at, archived, canonical_name, description, long_name)
VALUES (
    2,
    current_timestamp,
    current_timestamp,
    false,
    'program',
    'program broadcast with live stream, like weekly BSTV',
    'program video type'
);

----------------------------------------------- video test data -----------------------------------------------

INSERT INTO public.videos(id, video_type_id, created_at, updated_at, archived, visible, canonical_name, long_name, project_name, description, image_location, video_location)
VALUES (
    0,
    2,
    current_timestamp,
    current_timestamp,
    false,
    true,
    'bstv-adas-2019-04-11',
    'BSTV adás 2019. április 11.',
    'BSTV',
    'A kilencedik heti adásunkban a magyar költészet napja alkalmából beszélgettünk Lackfi Jánossal ...',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/keyframe/20190411_dori_lq.png',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/low_quality/20190411_dori_lq.mp4'
);

INSERT INTO public.videos(id, video_type_id, created_at, updated_at, archived, visible, canonical_name, long_name, project_name, description, image_location, video_location)
VALUES (
    1,
    2,
    current_timestamp,
    current_timestamp,
    false,
    true,
    'bstv-adas-2019-04-04',
    'BSTV adás 2019. április 4.',
    'BSTV',
    'Nyolcadik heti adásunkban ismét értesülhettetek a kollégiumot érintő hírekről. Első vendégünk volt a MŰSZAK vezetői, akik meséltek arról, hogy hogyan segítik a szakkolégiumok munkáját ...',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/keyframe/20190404_CsB_lq.png',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/low_quality/20190404_CsB_lq.mp4'
);

INSERT INTO public.videos(id, video_type_id, created_at, updated_at, archived, visible, canonical_name, long_name, project_name, description, image_location, video_location)
VALUES (
    2,
    0,
    current_timestamp,
    current_timestamp,
    false,
    true,
    'kavekostolas',
    'Kávékóstolás',
    'BSS Nyamm',
    'A kávézás és a kávé termesztés több száz éves múltra tekint vissza. A fogyasztása ma már széles körben elterjedt ...',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/keyframe/20190309_kavekostolas_lq.png',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/low_quality/20190309_kavekostolas_lq.mp4'
);

INSERT INTO public.videos(id, video_type_id, created_at, updated_at, archived, visible, canonical_name, long_name, project_name, description, image_location, video_location)
VALUES (
    3,
    0,
    current_timestamp,
    current_timestamp,
    false,
    true,
    'hk-tisztujitas-2019',
    'HK Tisztújítás 2019',
    'Szobakommandó',
    'A HK 2019-es Tisztújításáról kérdeztük a kollégistákat.',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/keyframe/20190325_hk_szobakommando_lq.png',
    'https://coding.sch.bme.hu/bss_vagott_web_16a9_SD/low_quality/20190325_hk_szobakommando_lq.mp4'
);
